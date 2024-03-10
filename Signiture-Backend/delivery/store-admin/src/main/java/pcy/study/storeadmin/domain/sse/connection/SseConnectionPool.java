package pcy.study.storeadmin.domain.sse.connection;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import pcy.study.storeadmin.domain.sse.connection.model.SseConnection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class SseConnectionPool implements ConnectionPool<Long> {

    private static final Map<Long, SseConnection> connectionPool = new ConcurrentHashMap<>();

    private final ObjectMapper objectMapper;

    @Override
    public void createConnection(Long key) {
        var sseEmitter = new SseEmitter(30 * 1000L);
        sseEmitter.onCompletion(() -> onCompletionCallback(key));
        sseEmitter.onTimeout(sseEmitter::complete);
        var userSseConnection = new SseConnection(objectMapper, sseEmitter);
        connectionPool.put(key, userSseConnection);
    }

    @Override
    public SseConnection getConnection(Long key) {
        return connectionPool.get(key);
    }

    @Override
    public void onCompletionCallback(Long key) {
        log.info("Call Back Connection Pool Completion: {}", connectionPool.get(key));
        connectionPool.remove(key);
    }
}
