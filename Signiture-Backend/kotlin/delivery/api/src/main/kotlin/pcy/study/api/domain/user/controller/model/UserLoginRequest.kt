package pcy.study.api.domain.user.controller.model

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

data class UserLoginRequest(
    @field:Schema(description = "이메일", example = "example@gmail.com")
    val email: @NotBlank String,
    @field:Schema(description = "비밀번호", example = "pws123")
    val password: @NotBlank String,
)