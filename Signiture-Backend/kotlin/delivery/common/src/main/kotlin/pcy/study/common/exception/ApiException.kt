package pcy.study.common.exception

import pcy.study.common.api.ApiCode

class ApiException @JvmOverloads constructor(
    val apiCode: ApiCode,
    override val message: String? = apiCode.getMessage(),
) : RuntimeException(message)
