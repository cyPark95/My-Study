package pcy.study.common.api.code

import pcy.study.common.api.ApiCode

enum class TokenErrorCode(
    private val httpStatusCode: Int,
    private val code: Int,
    private val message: String
) : ApiCode {

    TOKEN_EXCEPTION(400, 2000, "알 수 없는 토큰 에러입니다."),
    INVALID_TOKEN(400, 2001, "유효하지 않은 토큰입니다."),

    EXPIRED_TOKEN(401, 2002, "만료된 토큰입니다."),
    AUTHORIZATION_TOKEN_NOT_FOUND(401, 2003, "인증 정보가 없습니다."),
    ;

    override fun getHttpStatusCode(): Int {
        return this.httpStatusCode
    }

    override fun getCode(): Int {
        return this.code
    }

    override fun getMessage(): String {
        return message
    }
}