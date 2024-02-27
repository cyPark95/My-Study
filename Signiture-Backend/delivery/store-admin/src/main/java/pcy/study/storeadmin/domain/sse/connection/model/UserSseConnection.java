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

    private UserSseConnection(
            Long uniqueKey,
            ObjectMapper objectMapper,
            ConnectionPool<Long, UserSseConnection> connectionPool
    ) {
        this.uniqueKey = uniqueKey;
        this.sseEmitter = new SseEmitter();
        this.objectMapper = objectMapper;
        connect(connectionPool);
    }

    private void connect(ConnectionPool<Long, UserSseConnection> connectionPool) {
        this.sseEmitter.onCompletion(() -> connectionPool.onCompletionCallback(this));
        this.sseEmitter.onTimeout(this.sseEmitter::complete);
        connectionPool.addSession(this.uniqueKey, this);
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
        sendMessage(null, data);
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
