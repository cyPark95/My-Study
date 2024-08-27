package pcy.study.common.api

import jakarta.validation.Valid

data class ApiResponse<T>(
    var result: ApiResult? = null,
    @field:Valid
    var body: T? = null
) {

    companion object {

        @JvmStatic
        fun <T> ok(data: T): ApiResponse<T> {
            return ApiResponse(ApiResult.ok(), data)
        }

        @JvmStatic
        fun error(apiCode: ApiCode): ApiResponse<Unit> {
            return ApiResponse(ApiResult.error(apiCode))
        }

        @JvmStatic
        fun error(
            apiCode: ApiCode,
            message: String
        ): ApiResponse<Unit> {
            return ApiResponse(ApiResult.error(apiCode.getCode(), message))
        }
    }
}