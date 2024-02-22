package pcy.study.api.domain.token.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TokenResponse(
        @Schema(description = "엑세스 토큰")
        String accessToken,
        @Schema(description = "엑세스 토큰 만료시간")
        LocalDateTime accessTokenExpiredAt,
        @Schema(description = "리프레시 토큰")
        String refreshToken,
        @Schema(description = "리프레시 토큰 만료시간")
        LocalDateTime refreshTokenExpiredAt
) {
}
