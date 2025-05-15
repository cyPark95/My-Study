package was;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyWAS {

    private final int port;

    public MyWAS(int port) {
        this.port = port;
    }

    public void start() {
        try (ServerSocket socket = new ServerSocket(port)) {
            System.out.println("[Socket Server Info] " + socket);

            while (true) {
                Socket client = socket.accept();
                System.out.println("[Client Info] " + client);

                new Thread(() -> new HttpRequestHandler().mapping(client)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
