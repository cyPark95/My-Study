package pcy.study.common.api.code

import pcy.study.common.api.ApiCode

enum class ResponseCode(
    private val httpStatusCode: Int,
    private val code: Int,
    private val message: String
) : ApiCode {

    OK(200, 0, "성공"),
    ;

    override fun getHttpStatusCode(): Int {
        return httpStatusCode
    }

    override fun getCode(): Int {
        return code
    }

    override fun getMessage(): String {
        return message
    }
}