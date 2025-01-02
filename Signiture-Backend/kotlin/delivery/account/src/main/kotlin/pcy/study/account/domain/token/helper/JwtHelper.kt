package pcy.study.account.domain.token.helper

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import pcy.study.account.common.Log
import pcy.study.account.domain.token.model.Token
import pcy.study.common.api.code.TokenErrorCode
import pcy.study.common.exception.ApiException
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtHelper(
    @Value("\${token.secret.key}")
    secretKey: String,
    @Value("\${token.access-token.plus-hour}")
    private val accessTokenPlusHour: Long,
    @Value("\${token.refresh-token.plus-hour}")
    private val refreshTokenPlusHour: Long
) : TokenHelper {

    companion object: Log
    private val secretKey: SecretKey = Keys.hmacShaKeyFor(secretKey.toByteArray())

    override fun issueAccessToken(data: Map<String, Any>): Token {
        return generatedToken(data, accessTokenPlusHour)
    }

    override fun issueRefreshToken(data: Map<String, Any>): Token {
        return generatedToken(data, refreshTokenPlusHour)
    }

    override fun validationTokenWithThrow(token: String): Map<String, Any> {
        val parser = Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()

        return try {
            val result = parser.parseClaimsJws(token)
            HashMap(result.body)
        } catch (e: Exception) {
            log.warn("JWT Exception Message: {}", e.message)

            when (e) {
                is SignatureException -> {
                    throw ApiException(TokenErrorCode.INVALID_TOKEN, e.message)
                }

                is ExpiredJwtException -> {
                    throw ApiException(TokenErrorCode.EXPIRED_TOKEN, e.message)
                }

                else -> {
                    throw ApiException(TokenErrorCode.TOKEN_EXCEPTION, e.message)
                }
            }
        }
    }

    private fun generatedToken(data: Map<String, Any>, expiredPlusHour: Long): Token {
        val expiredLocalDateTime = LocalDateTime.now().plusHours(expiredPlusHour)
        val expiredAt = Date.from(expiredLocalDateTime.atZone(ZoneId.systemDefault()).toInstant())
        val token = Jwts.builder()
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .setClaims(data)
            .setExpiration(expiredAt)
            .compact()
        return Token(token, expiredLocalDateTime)
    }
}