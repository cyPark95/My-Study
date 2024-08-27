package pcy.study.common.api.code

import pcy.study.common.api.ApiCode

enum class StoreMenuErrorCode(
    private val httpStatusCode: Int,
    private val code: Int,
    private val message: String
) : ApiCode {

    STORE_MENU_NOT_FOUND(400, 4001, "가게 메뉴를 찾을 수 없습니다."),
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