import java.io.*;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;

public class MyHttpResponse {
    MyHttpRequest request;

    public MyHttpResponse(MyHttpRequest request) throws IOException, InterruptedException, NoSuchAlgorithmException {
        this.request = request;
        if (request.isWebSocketRequest()) {
            Socket clientSocket = request.getClientSocket();
            MyHttpRequest.handshake(clientSocket, request.getMap().get("Sec-WebSocket-Key"));
            // 1. decode the message -> payload (join / message/ leave);
            // 2. handle the message / send message back
            while (true) {
                DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                String message = decodeMessage(in);
                System.out.println("the message sent from client is " + message);
                sendMessage(message, clientSocket);
            }
        } else {
            OutputStream os = request.getClientSocket().getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            String filename = "./resources/" + request.getFilename();
            File accessFile = new File(filename);
            int accessFileLength = (int) accessFile.length();
            if (accessFile.exists()) {
                System.out.println("file exists");
                FileInputStream fis = new FileInputStream(accessFile);
                byte[] fBytes = new byte[accessFileLength];
                fis.read(fBytes);
                fis.close();
                dos.writeBytes("HTTP/1.1 200 OK \r\n");
                String extension = filename.substring(filename.lastIndexOf('.') + 1);
                dos.writeBytes("Content-Type: text/" + extension + "; charset=utf-8;\r\n");
                dos.writeBytes("Content-Length: " + accessFileLength + "\r\n");
                dos.writeBytes("\r\n");
                dos.write(fBytes, 0, accessFileLength);
            } else {
                dos.writeBytes("HTTP/1.0 404 Not Found \r\n");
                dos.writeBytes("Connection: close\r\n");
            }
            dos.writeBytes("\r\n");
//            Thread.sleep(10);
            dos.flush();
        }
    }

    // decode websocket header + payload
    private static String decodeMessage(DataInputStream in) throws IOException {
        System.out.println("about to read message");
        byte[] bytes = in.readNBytes(2); // FIN (1) RSV (1) RSV (1) RSV (1) OPC (4) | MSK (1) LEN (7)
        int finBit = bytes[0] & 0xff >> 7;
        int opcode = bytes[0] & 0x0F;
        int maskBit = bytes[1] & 0x80;
        int payloadLenBits = bytes[1] & 0x7f;
        System.out.println("the fin bit is " + finBit);
        System.out.println("the opcode is " + opcode);
        if (maskBit != 0) System.out.println("message is masked");
        long payloadLen = 0; // the actual payload length
        if (payloadLenBits == 127) {
            // highly unlikely
            payloadLen = in.readLong();
            System.out.println("payload length is so long, it is " + payloadLen);
        } else if (payloadLenBits == 126) {
            // unlikely
            payloadLen = in.readShort();
            System.out.println("payload length is longer, it is " + payloadLen);
        } else {
            // usually the message is very short
            payloadLen = payloadLenBits;
            System.out.println("payload length is " + payloadLen);
        }
        byte[] maskingKey = in.readNBytes(4);
        byte[] encoded = in.readNBytes((int) payloadLen);
        byte[] decoded = new byte[encoded.length];
        for (int i = 0; i < encoded.length; i++) {
            decoded[i] = (byte) (encoded[i] ^ maskingKey[i % 4]);
        }
        return new String(decoded);
    }

    private static void sendMessage(String asciiMsg, Socket client) throws IOException {
        DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());
        // send the ascii msg as a ws msg
        outputStream.writeByte(0x81); // 1st byte: 1000 0001
        // 2st byte is maskBit lengthOfMsg
        // 0_______
        if (asciiMsg.length() > 65000) {
            outputStream.writeByte(127);
            outputStream.writeLong(asciiMsg.length());
        } else if (asciiMsg.length() >= 126) {
            // 2nd byte: assume msg length < 65K
            outputStream.writeByte(126);
            outputStream.writeShort(asciiMsg.length());
        } else {
            outputStream.writeByte(asciiMsg.length());
        }
        outputStream.writeBytes(asciiMsg);
        outputStream.flush();
    }
}