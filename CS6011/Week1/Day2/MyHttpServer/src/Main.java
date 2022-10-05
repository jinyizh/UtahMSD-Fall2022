import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException{
        ServerSocket server = new ServerSocket(8080);
        Socket clientSocket;
        while(true){
            clientSocket = server.accept();
            try {
                HTTPRequest request = new HTTPRequest(clientSocket);
                HTTPResponse response = new  HTTPResponse(request);
            }
            finally{
                clientSocket.close();
            }
        }
    }
}