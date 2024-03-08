package pcy.study.storeadmin.domain.message.connection;

public interface Connection {

    void connect();

    void sendMessage(String name, Object data);
}
