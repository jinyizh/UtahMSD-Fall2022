import java.io.*;
import java.security.NoSuchAlgorithmException;

public class MyHttpResponse {

    public MyHttpResponse(MyHttpRequest request) throws IOException, InterruptedException, NoSuchAlgorithmException {
        if (request.isWebSocketRequest()) {
            // handshake(request.getClientSocket(), "key-");
            MyHttpRequest.handshake(request.getClientSocket(), request.getMap().get("Sec-WebSocket-Key"));
            // 1. decode the message -> payload (join / message/ leave);
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
}