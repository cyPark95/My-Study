package pcy.study.storeadmin.domain.sse.connection;

import pcy.study.storeadmin.domain.sse.connection.model.SseConnection;

public interface ConnectionPool<T> {

    void createConnection(T key);

    SseConnection getConnection(T key);

    void onCompletionCallback(T key);
}
