package pcy.study.storeadmin.domain.sse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import pcy.study.storeadmin.domain.authorization.model.StoreUserDetails;
import pcy.study.storeadmin.domain.sse.connection.ConnectionPool;
import pcy.study.storeadmin.domain.sse.connection.model.UserSseConnection;

import java.util.Optional;

@Tag(name = "가맹점 주문 API", description = "인증된 사용자가 접근할 수 있습니다.")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sse")
public class SseApiController {

    private final ObjectMapper objectMapper;
    private final ConnectionPool<Long, UserSseConnection> connectionPool;

    @Operation(summary = "SSE 연결", description = "주문 접수 이벤트 처리를 위해 API와 연결합니다.")
    @GetMapping(value = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseBodyEmitter connect(
            @Parameter(hidden = true)
            @AuthenticationPrincipal StoreUserDetails userDetails
    ) {
        log.info("Login User {}", userDetails);
        var userSseConnection = UserSseConnection.connect(
                userDetails.storeId(),
                objectMapper,
                connectionPool
        );

        return userSseConnection.getSseEmitter();
    }

    @Operation(summary = "메시지 전송", description = "PUSH 메시지를 전송합니다.")
    @GetMapping("/push-event")
    public void pushEvent(
            @Parameter(hidden = true)
            @AuthenticationPrincipal StoreUserDetails userDetails
    ) {
        var userSseConnection = connectionPool.getSession(userDetails.storeId());
        Optional.ofNullable(userSseConnection)
                .ifPresent(it -> it.sendMessage("Hello World"));
    }
}
