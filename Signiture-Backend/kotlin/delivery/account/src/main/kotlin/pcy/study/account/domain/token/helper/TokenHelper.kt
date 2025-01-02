package pcy.study.account.domain.token.helper

import pcy.study.account.domain.token.model.Token

interface TokenHelper {

    fun issueAccessToken(data: Map<String, Any>): Token
    fun issueRefreshToken(data: Map<String, Any>): Token

    fun validationTokenWithThrow(token: String): Map<String, Any>
}