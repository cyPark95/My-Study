package pcy.study.api.domain.token.converter;

import pcy.study.api.common.annotation.Converter;
import pcy.study.api.common.api.error.ErrorCode;
import pcy.study.api.common.exception.ApiException;
import pcy.study.api.domain.token.controller.model.TokenResponse;
import pcy.study.api.domain.token.model.Token;

import java.util.Objects;

@Converter
public class TokenConverter {

    public TokenResponse toResponse(Token accessToken, Token refreshToken) {
        Objects.requireNonNull(accessToken, () -> {
            throw new ApiException("AccessToken is Null", ErrorCode.NULL_POINT);
        });
        Objects.requireNonNull(refreshToken, () -> {
            throw new ApiException("RefreshToken is Null", ErrorCode.NULL_POINT);
        });

        return TokenResponse.builder()
                .accessToken(accessToken.token())
                .accessTokenExpiredAt(accessToken.expiredAt())
                .refreshToken(refreshToken.token())
                .refreshTokenExpiredAt(refreshToken.expiredAt())
                .build();
    }
}
