package pcy.study.common.api.code

import pcy.study.common.api.ApiCode

enum class StoreErrorCode(
    private val httpStatusCode: Int,
    private val code: Int,
    private val message: String
) : ApiCode {

    STORE_NOT_FOUND(400, 3001, "가게를 찾을 수 없습니다."),
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