package pcy.study.api.domain.token.helper;

import pcy.study.api.domain.token.model.Token;

import java.util.Map;

public interface TokenHelper {

    Token issueAccessToken(Map<String, Object> data);
    Token issueRefreshToken(Map<String, Object> data);

    Map<String, Object> validationTokenWithThrow(String token);
}
