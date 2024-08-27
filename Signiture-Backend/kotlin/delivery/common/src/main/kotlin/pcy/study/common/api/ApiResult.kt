package pcy.study.common.api

import pcy.study.common.api.code.ResponseCode

data class ApiResult(
    val code: Int,
    val message: String? = null,
) {

    companion object {

        fun ok(): ApiResult {
            return create(ResponseCode.OK)
        }

        fun error(apiCode: ApiCode): ApiResult {
            return create(apiCode)
        }

        fun error(
            code: Int,
            message: String,
        ): ApiResult {
            return ApiResult(
                code = code,
                message = message
            )
        }

        private fun create(apiCode: ApiCode): ApiResult {
            return ApiResult(
                code = apiCode.getCode(),
                message = apiCode.getMessage()
            )
        }
    }
}