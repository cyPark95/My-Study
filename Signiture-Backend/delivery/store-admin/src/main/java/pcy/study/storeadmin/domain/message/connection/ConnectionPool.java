package pcy.study.storeadmin.domain.message.connection;

public interface ConnectionPool<T> {

    void createConnection(T key);

    Connection getConnection(T key);

    void onCompletionCallback(T key);
}
