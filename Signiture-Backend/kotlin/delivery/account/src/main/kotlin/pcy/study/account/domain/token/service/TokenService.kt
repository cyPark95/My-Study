package pcy.study.account.domain.token.service

import org.springframework.stereotype.Service
import pcy.study.account.domain.token.helper.TokenHelper
import pcy.study.account.domain.token.model.Token

@Service
class TokenService(
    private val tokenHelper: TokenHelper
) {

    fun issueAccessToken(userId: Long): Token {
        val data = mapOf("userId" to userId)
        return tokenHelper.issueAccessToken(data)
    }

    fun issueRefreshToken(randomToken: String): Token {
        val data = mapOf("randomToken" to randomToken)
        return tokenHelper.issueRefreshToken(data)
    }

    fun validationAccessToken(accessToken: String): Long {
        return accessToken.let { token ->
            tokenHelper.validationTokenWithThrow(token)
        }.let { map ->
            map["userId"]
        }.let { userId ->
            userId.toString().toLong()
        }
    }
}