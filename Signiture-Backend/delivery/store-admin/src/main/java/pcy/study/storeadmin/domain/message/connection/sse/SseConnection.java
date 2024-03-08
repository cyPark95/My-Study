package pcy.study.storeadmin.domain.message.connection.sse;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import pcy.study.storeadmin.domain.message.connection.Connection;

@Slf4j
@Getter
@ToString
public class SseConnection implements Connection {

    private final SseEmitter sseEmitter;
    private final ObjectMapper objectMapper;

    public SseConnection(
            ObjectMapper objectMapper,
            SseEmitter sseEmitter
    ) {
        this.objectMapper = objectMapper;
        this.sseEmitter = sseEmitter;
        connect();
    }

    @Override
    public void connect() {
        sendMessage("onopen", "connect");
    }

    @Override
    public void sendMessage(String name, Object data) {
        try {
            var jsonData = this.objectMapper.writeValueAsString(data);
            var event = SseEmitter.event()
                    .name(name)
                    .data(jsonData);

            this.sseEmitter.send(event);
        } catch (Exception e) {
            log.error("SseEmitter Send Message Error", e);
            this.sseEmitter.completeWithError(e);
        }
    }
}
