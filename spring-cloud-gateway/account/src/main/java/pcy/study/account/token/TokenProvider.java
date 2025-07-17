package pcy.study.authentication.token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class TokenProvider {

    private static final String secret = "SpringCloudGatewayJWTSecret";
    private static final long expiredTime = 5;

    private final SecretKey key;
    
    public TokenProvider() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String username) {
        Date expiredAt = getExpiredAt();

        return Jwts.builder()
                .signWith(key)
                .subject(username)
                .expiration(expiredAt)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser().build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    private Date getExpiredAt() {
        LocalDateTime expiredDateTime = LocalDateTime.now().plusMinutes(expiredTime);
        Instant instant = expiredDateTime
                .atZone(ZoneId.systemDefault())
                .toInstant();
        return Date.from(instant);
    }
}
