import java.net.Socket;

public class ConnectionHandler implements Runnable {

    private Socket clientSocket;

    public ConnectionHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        // handle the request headers

        // send the response

        // done using client socket
    }
}
