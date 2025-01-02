package pcy.study.account.domain.token.business

import pcy.study.account.domain.token.controller.model.TokenResponse
import pcy.study.account.domain.token.controller.model.TokenValidationResponse
import pcy.study.account.domain.token.converter.TokenConverter
import pcy.study.account.domain.token.service.TokenService
import pcy.study.common.annotation.Business
import java.util.*

@Business
class TokenBusiness(
    private val tokenService: TokenService,
    private val tokenConverter: TokenConverter
) {

    fun issueToken(userId: Long): TokenResponse {
        val accessToken = tokenService.issueAccessToken(userId)
        val randomToken = UUID.randomUUID().toString()
        val refreshToken = tokenService.issueRefreshToken(randomToken)
        return tokenConverter.toTokenResponse(accessToken, refreshToken)
    }

    fun validationAccessToken(accessToken: String): TokenValidationResponse {
        val userId = tokenService.validationAccessToken(accessToken)
        return tokenConverter.toTokenValidationResponse(userId)
    }
}