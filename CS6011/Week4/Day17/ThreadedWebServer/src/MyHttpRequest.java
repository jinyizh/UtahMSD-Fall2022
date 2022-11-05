import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class MyHttpRequest {

    private String filename;
    private Socket clientSocket;
    private HashMap<String, String> map = new HashMap<>();

    public String getFilename() {
        return filename;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public HashMap<String, String> getMap() {
        return this.map;
    }

    public MyHttpRequest(Socket clientSocket) {

        this.clientSocket = clientSocket;
        System.out.println("Client joined: " + clientSocket);

        Scanner sc;
        try {
            sc = new Scanner(clientSocket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // handle 1st line of header
        String line = sc.nextLine();
        System.out.println("just got: " + line);
        String[] lineSplit = line.split(" ");
        filename = lineSplit[1];
        if (Objects.equals(filename, "/")) {
            filename = "/index.html";
        }

        // handle all the rest of the header (store in hashmap TODO)
        line = sc.nextLine();
        while (!line.equals("")) {
            System.out.println("got line: " + line);
            String[] restLineSplit = line.split(": ");
            map.put(restLineSplit[0], restLineSplit[1]);
            line = sc.nextLine();
        }
        System.out.println("done parsing headers");
    }

    public boolean isWebSocketRequest() {
        return map.containsKey("Sec-WebSocket-Key");
        // header also contains "Connection: Upgrade"
    }

    // Server handshake response:
    public static void handshake(Socket clientSocket, String key) throws IOException, NoSuchAlgorithmException {
        OutputStream outputStream = clientSocket.getOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream);

        System.out.println("Key: " + key);
        key += "258EAFA5-E914-47DA-95CA-C5AB0DC85B11"; // magic string
        System.out.println("Key: '" + key + "'");
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] data = key.getBytes();
        String result = Base64.getEncoder().encodeToString(md.digest(data));

        // send response back to client asking for a web socket
        printWriter.print("HTTP/1.1 101 Switching Protocols\r\n");
        printWriter.print("Upgrade: websocket\r\n");
        printWriter.print("Connection: Upgrade\r\n");
        printWriter.print("Sec-WebSocket-Accept: " + result + "\r\n");
//        printWriter.print("Sec-WebSocket-Extensions: permessage-deflate; client_no_context_takeover\r\n");
        //printWriter.print("Sec-WebSocket-Extensions:\r\n");
        printWriter.print("\r\n");
        printWriter.flush();
        System.out.println("done sending handshake");
        while( true ) {
            InputStream in = clientSocket.getInputStream();
            System.out.println("about to read");
            byte[] x = in.readNBytes(2);
            System.out.println( "read: " + x[0] + ", " + x[1] );
        }
    }
}