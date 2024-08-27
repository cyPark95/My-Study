package pcy.study.common.api.code

import pcy.study.common.api.ApiCode

enum class UserErrorCode(
    private val httpStatusCode: Int,
    private val code: Int,
    private val message: String
) : ApiCode {

    USER_NOT_FOUND(400, 1404, "사용자를 찾을 수 없습니다."),
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