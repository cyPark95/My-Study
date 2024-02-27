package pcy.study.storeadmin.domain.sse.connection;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pcy.study.storeadmin.domain.sse.connection.model.UserSseConnection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class SseConnectionPool implements ConnectionPool<Long, UserSseConnection> {

    private static final Map<Long, UserSseConnection> connectionPool = new ConcurrentHashMap<>();

    @Override
    public void addSession(Long key, UserSseConnection session) {
        connectionPool.put(key, session);
    }

    @Override
    public UserSseConnection getSession(Long key) {
        return connectionPool.get(key);
    }

    @Override
    public void onCompletionCallback(UserSseConnection session) {
        log.info("Call Back Connection Pool Completion: {}", session);
        connectionPool.remove(session.getUniqueKey());
    }
}
