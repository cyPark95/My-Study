package pcy.study.storeadmin.domain.sse.connection.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import pcy.study.storeadmin.domain.sse.connection.ConnectionPool;

import java.io.IOException;

@Getter
@ToString
public class UserSseConnection {

    private final Long uniqueKey;
    private final SseEmitter sseEmitter;
    private final ObjectMapper objectMapper;
    private final ConnectionPool<Long, UserSseConnection> connectionPool;

    private UserSseConnection(
            Long uniqueKey,
            ObjectMapper objectMapper,
            ConnectionPool<Long, UserSseConnection> connectionPool
    ) {
        this.uniqueKey = uniqueKey;
        this.sseEmitter = new SseEmitter(30 * 1000L);
        this.objectMapper = objectMapper;
        this.connectionPool = connectionPool;
        connect();
    }

    private void connect() {
        this.sseEmitter.onCompletion(() -> this.connectionPool.onCompletionCallback(this));
        this.sseEmitter.onTimeout(this.sseEmitter::complete);
        sendMessage("onopen", "connect");
    }

    public static UserSseConnection connect(
            Long uniqueKey,
            ObjectMapper objectMapper,
            ConnectionPool<Long, UserSseConnection> connectionPool
    ) {
        return new UserSseConnection(uniqueKey, objectMapper, connectionPool);
    }

    public void sendMessage(Object data) {
        try {
            var jsonData = this.objectMapper.writeValueAsString(data);
            var event = SseEmitter.event()
                    .data(jsonData);

            this.sseEmitter.send(event);
        } catch (IOException e) {
            this.sseEmitter.completeWithError(e);
        }
    }

    public void sendMessage(String name, Object data) {
        try {
            var jsonData = this.objectMapper.writeValueAsString(data);
            var event = SseEmitter.event()
                    .name(name)
                    .data(jsonData);

            this.sseEmitter.send(event);
        } catch (IOException e) {
            this.sseEmitter.completeWithError(e);
        }
    }
}
