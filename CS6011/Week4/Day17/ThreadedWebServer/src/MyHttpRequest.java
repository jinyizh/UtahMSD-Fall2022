import java.io.IOException;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class MyHttpRequest {

    private String filename;
    private Socket clientSocket;

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
        System.out.println(line);
        String[] lineSplit = line.split(" ");
        filename = lineSplit[1];
        if (Objects.equals(filename, "/")) {
            filename = "/index.html";
        }

        // handle all the rest of the header
        line = sc.nextLine();
        while (!line.equals("")) {
            System.out.println(line);
            line = sc.nextLine();
        }
    }

    public String getFilename() {
        return filename;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }
}