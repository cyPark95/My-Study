package pcy.study.account.domain.token.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pcy.study.account.domain.token.business.TokenBusiness
import pcy.study.account.domain.token.controller.model.TokenValidationRequest
import pcy.study.account.domain.token.controller.model.TokenValidationResponse

@RestController
@RequestMapping("/internal-api/token")
class TokenInternalApiController(
    private val tokenBusiness: TokenBusiness
) {

    @PostMapping("/validation")
    fun tokenValidation(@RequestBody request: TokenValidationRequest): TokenValidationResponse {
        return tokenBusiness.validationAccessToken(request.token)
    }
}