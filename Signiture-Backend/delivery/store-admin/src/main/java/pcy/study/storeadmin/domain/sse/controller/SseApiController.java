package pcy.study.storeadmin.domain.sse.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import pcy.study.storeadmin.domain.authorization.model.StoreUserDetails;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Tag(name = "가맹점 주문 API", description = "인증된 사용자가 접근할 수 있습니다.")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sse")
public class SseApiController {

    private static final Map<Long, SseEmitter> userConnection = new ConcurrentHashMap<>();

    @Operation(summary = "SSE 연결", description = "주문 접수 이벤트 처리를 위해 API와 연결합니다.")
    @GetMapping(value = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseBodyEmitter connect(
            @Parameter(hidden = true)
            @AuthenticationPrincipal StoreUserDetails userDetails
    ) {
        log.info("Login User {}", userDetails);

        var emitter = new SseEmitter(1000L * 10);
        userConnection.put(userDetails.userId(), emitter);

        // 클라이언트 타임아웃 이벤트
        emitter.onTimeout(() -> {
            log.info("On Timeout");
            emitter.complete();
        });

        // 클라이언트 연결 종료 이벤트
        emitter.onCompletion(() -> {
            log.info("On Completion");
            userConnection.remove(userDetails.userId());
        });

        var event = SseEmitter
                .event()
                .name("open")
                .data("connect");

        try {
            emitter.send(event);
        } catch (IOException e) {
            emitter.completeWithError(e);
        }

        return emitter;
    }

    @Operation(summary = "메시지 전송", description = "PUSH 메시지를 전송합니다.")
    @GetMapping("/push-event")
    public void pushEvent(
            @Parameter(hidden = true)
            @AuthenticationPrincipal StoreUserDetails userDetails
    ) {
        var emitter = userConnection.get(userDetails.userId());

        var event = SseEmitter
                .event()
                .data("hello");

        try {
            emitter.send(event);
        } catch (IOException e) {
            emitter.completeWithError(e);
        }
    }
}
