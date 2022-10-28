import java.io.IOException;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class MyHttpRequest {

    private String filename;
    private Socket clientSocket;
    public MyHttpRequest(Socket clientSocket) {
        this.clientSocket = clientSocket;
        System.out.println("Client joined : " + clientSocket);
        Scanner sc;
        try {
            sc = new Scanner(clientSocket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String line = sc.nextLine();
        System.out.println(line);
        System.out.println(sc.nextLine());
        System.out.println(sc.nextLine());
        String[] lineSplit = line.split(" ");
        filename = lineSplit[1];
        if (Objects.equals(filename, "/")) {
            filename = "/index.html";
        }
    }

    public String getFilename() {
        return filename;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }
}