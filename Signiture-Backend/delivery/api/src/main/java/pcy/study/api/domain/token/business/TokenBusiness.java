package pcy.study.api.domain.token.business;

import lombok.RequiredArgsConstructor;
import pcy.study.api.common.annotation.Business;
import pcy.study.api.common.api.error.ErrorCode;
import pcy.study.api.common.exception.ApiException;
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
            throw new ApiException("User ID is Null", ErrorCode.NULL_POINT);
        });

        var accessToken = tokenService.issueAccessToken(userId);
        var randomToken = UUID.randomUUID().toString();
        var refreshToken = tokenService.issueRefreshToken(randomToken);
        var response = tokenConverter.toResponse(accessToken, refreshToken);
        return response;
    }
}
