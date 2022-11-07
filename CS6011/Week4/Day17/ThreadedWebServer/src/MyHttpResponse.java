import java.io.*;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;

public class MyHttpResponse {
    MyHttpRequest request;

    public MyHttpResponse(MyHttpRequest request) throws IOException, InterruptedException, NoSuchAlgorithmException {
        this.request = request;
        if (request.isWebSocketRequest()) {
            // handshake(request.getClientSocket(), "key-");
            Socket clientSocket = request.getClientSocket();
            MyHttpRequest.handshake(clientSocket, request.getMap().get("Sec-WebSocket-Key"));
            // 1. decode the message -> payload (join / message/ leave);
            while (true) {
                InputStream in = clientSocket.getInputStream();
                decodeMessage(in);
            }
            // 2. handle the message / send message back
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

    // decode websocket header + payload
    private static void decodeMessage(InputStream in) throws IOException {
        System.out.println("about to read message");
        byte[] bytes = in.readNBytes(2);
        System.out.println("read: " + bytes[0] + ", " + bytes[1]);
    }
}