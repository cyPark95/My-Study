package pcy.study.common.api.code

import pcy.study.common.api.ApiCode

enum class ErrorCode(
    private val httpStatusCode: Int,
    private val code: Int,
    private val message: String
) : ApiCode {

    BAD_REQUEST(400, 400, "클라이언트 오류"),
    ACCESS_DENIED(401, 401, "인증 실패 오류"),
    NOT_FOUNT(404, 404, "리소스 접근 오류"),
    METHOD_NOT_ALLOWED(405, 405, "HTTP 메서드 오류"),

    SERVER_ERROR(500, 500, "서버 오류"),
    NULL_POINT(512, 512, "Null Point"),
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