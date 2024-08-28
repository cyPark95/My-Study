package pcy.study.api.domain.token.business;

import lombok.RequiredArgsConstructor;
import pcy.study.common.annotation.Business;
import pcy.study.common.api.code.ErrorCode;
import pcy.study.common.exception.ApiException;
import pcy.study.api.domain.token.controller.model.TokenResponse;
import pcy.study.api.domain.token.converter.TokenConverter;
import pcy.study.api.domain.token.service.TokenService;

import java.util.Objects;
import java.util.UUID;

@Business
@RequiredArgsConstructor
public class TokenBusiness {

    private final TokenService tokenService;
    private final TokenConverter tokenConverter;

    public TokenResponse issueToken(Long userId) {
        Objects.requireNonNull(userId, () -> {
            throw new ApiException(ErrorCode.NULL_POINT, "User ID is Null");
        });

        var accessToken = tokenService.issueAccessToken(userId);
        var randomToken = UUID.randomUUID().toString();
        var refreshToken = tokenService.issueRefreshToken(randomToken);
        return tokenConverter.toResponse(accessToken, refreshToken);
    }

    public Long validationAccessToken(String accessToken) {
        return tokenService.validationAccessToken(accessToken);
    }
}
