package pcy.study.account.domain.token.converter

import pcy.study.account.domain.token.controller.model.TokenResponse
import pcy.study.account.domain.token.controller.model.TokenValidationResponse
import pcy.study.account.domain.token.model.Token
import pcy.study.common.annotation.Converter

@Converter
class TokenConverter {

    fun toTokenValidationResponse(
        userId: Long
    ): TokenValidationResponse {
        return TokenValidationResponse(userId = userId)
    }

    fun toTokenResponse(
        accessToken: Token,
        refreshToken: Token
    ): TokenResponse {
        return TokenResponse(
            accessToken = accessToken.token,
            accessTokenExpiredAt = accessToken.expiredAt,
            refreshToken = refreshToken.token,
            refreshTokenExpiredAt = refreshToken.expiredAt,
        )
    }
}