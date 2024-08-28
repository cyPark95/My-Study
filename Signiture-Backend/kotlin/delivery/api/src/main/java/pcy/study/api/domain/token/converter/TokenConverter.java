package pcy.study.api.domain.token.converter;

import pcy.study.common.annotation.Converter;
import pcy.study.common.api.code.ErrorCode;
import pcy.study.common.exception.ApiException;
import pcy.study.api.domain.token.controller.model.TokenResponse;
import pcy.study.api.domain.token.model.Token;

import java.util.Objects;

@Converter
public class TokenConverter {

    public TokenResponse toResponse(Token accessToken, Token refreshToken) {
        Objects.requireNonNull(accessToken, () -> {
            throw new ApiException(ErrorCode.NULL_POINT, "AccessToken is Null");
        });
        Objects.requireNonNull(refreshToken, () -> {
            throw new ApiException(ErrorCode.NULL_POINT, "RefreshToken is Null");
        });

        return TokenResponse.builder()
                .accessToken(accessToken.token())
                .accessTokenExpiredAt(accessToken.expiredAt())
                .refreshToken(refreshToken.token())
                .refreshTokenExpiredAt(refreshToken.expiredAt())
                .build();
    }
}
