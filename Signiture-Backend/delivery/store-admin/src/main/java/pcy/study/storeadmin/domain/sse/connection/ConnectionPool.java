package pcy.study.storeadmin.domain.sse.connection;

public interface ConnectionPool<T, R> {

    void addSession(T key, R session);

    R getSession(T key);

    void onCompletionCallback(R session);
}
