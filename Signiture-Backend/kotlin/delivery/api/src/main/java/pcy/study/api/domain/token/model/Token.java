package pcy.study.api.domain.token.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Token(
        @Schema(description = "토큰")
        String token,
        @Schema(description = "만료시간")
        LocalDateTime expiredAt
) {
}
