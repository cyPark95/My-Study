package pcy.study.api.domain.token.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pcy.study.common.api.code.ErrorCode;
import pcy.study.common.exception.ApiException;
import pcy.study.api.domain.token.helper.TokenHelper;
import pcy.study.api.domain.token.model.Token;

import java.util.HashMap;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenHelper tokenHelper;

    public Token issueAccessToken(Long userId) {
        var data = new HashMap<String, Object>();
        data.put("userId", userId);
        return tokenHelper.issueAccessToken(data);
    }

    public Token issueRefreshToken(String randomToken) {
        var data = new HashMap<String, Object>();
        data.put("randomToken", randomToken);
        return tokenHelper.issueRefreshToken(data);
    }

    public Long validationAccessToken(String accessToken) {
        var map = tokenHelper.validationTokenWithThrow(accessToken);
        var userId = map.get("userId");

        Objects.requireNonNull(userId, () -> {
            throw new ApiException(ErrorCode.NULL_POINT, "User ID is Null in Access Token");
        });

        return Long.parseLong(userId.toString());
    }
}
