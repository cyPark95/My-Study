package pcy.study.sns.common.security.token;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pcy.study.sns.common.security.dto.Token;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {

    private static final int MILLISECONDS_TO_SECONDS = 1000;
    private static final String TOKEN_CLAIM_KEY = "username";

    private final Key key;
    private final Long accessTokenExpiredTime;

    public JwtProvider(
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.token-validate-in-seconds}") Long tokenValidateInSeconds
    ) {
        this.key = getSecretKey(secretKey);
        this.accessTokenExpiredTime = tokenValidateInSeconds;
    }

    public Token getToken(String username) {
        var now = new Date();
        var expireDate = new Date(now.getTime() + accessTokenExpiredTime * MILLISECONDS_TO_SECONDS);

        var token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .claim(TOKEN_CLAIM_KEY, username)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return new Token(token, expireDate.getTime());
    }

    public String getClaim(String token) throws UnsupportedJwtException, MalformedJwtException, SignatureException, ExpiredJwtException, IllegalArgumentException {
        var claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.get(TOKEN_CLAIM_KEY, String.class);
    }

    private Key getSecretKey(String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
