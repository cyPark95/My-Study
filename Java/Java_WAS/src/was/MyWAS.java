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
        // port 에 바인딩 되는 ServerSocket 생성
        try (ServerSocket socket = new ServerSocket(port)) {
            System.out.println("[Socket Server Info] " + socket);

            // 소켓 서버 무한루프
            while (true) {
                // socket 연결 수신 대기
                Socket client = socket.accept();
                System.out.println("[Client Info] " + client);

                // 요청 처리 스레드
                new Thread(() -> new HttpRequestHandler().mapping(client)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
