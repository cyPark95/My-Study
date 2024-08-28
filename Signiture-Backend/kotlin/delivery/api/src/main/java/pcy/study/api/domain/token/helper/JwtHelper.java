package pcy.study.api.domain.token.helper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pcy.study.common.api.code.TokenErrorCode;
import pcy.study.common.exception.ApiException;
import pcy.study.api.domain.token.model.Token;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtHelper implements TokenHelper {

    private final SecretKey secretKey;
    private final Long accessTokenPlusHour;
    private final Long refreshTokenPlusHour;

    public JwtHelper(
            @Value("${token.secret.key}")
            String secretKey,
            @Value("${token.access-token.plus-hour}")
            Long accessTokenPlusHour,
            @Value("${token.refresh-token.plus-hour}")
            Long refreshTokenPlusHour
    ) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.accessTokenPlusHour = accessTokenPlusHour;
        this.refreshTokenPlusHour = refreshTokenPlusHour;
    }

    @Override
    public Token issueAccessToken(Map<String, Object> data) {
        return generatedToken(data, accessTokenPlusHour);
    }

    @Override
    public Token issueRefreshToken(Map<String, Object> data) {
        return generatedToken(data, refreshTokenPlusHour);
    }

    private Token generatedToken(Map<String, Object> data, Long expiredPlusHour) {
        var expiredLocalDateTime = LocalDateTime.now().plusHours(expiredPlusHour);
        var expiredAt = Date.from(expiredLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());
        var token = Jwts.builder()
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .setClaims(data)
                .setExpiration(expiredAt)
                .compact();

        return Token.builder()
                .token(token)
                .expiredAt(expiredLocalDateTime)
                .build();
    }

    @Override
    public Map<String, Object> validationTokenWithThrow(String token) {
        var parser = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build();

        try {
            var result = parser.parseClaimsJws(token);
            return new HashMap<>(result.getBody());
        } catch (SignatureException e) {
            throw new ApiException(TokenErrorCode.INVALID_TOKEN, e.getMessage());

        } catch (ExpiredJwtException e) {
            throw new ApiException(TokenErrorCode.EXPIRED_TOKEN, e.getMessage());

        } catch (Exception e) {
            throw new ApiException(TokenErrorCode.TOKEN_EXCEPTION, e.getMessage());
        }
    }
}
