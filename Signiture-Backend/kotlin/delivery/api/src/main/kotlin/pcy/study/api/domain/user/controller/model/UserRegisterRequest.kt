package pcy.study.api.domain.user.controller.model

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class UserRegisterRequest(
    @field:Schema(description = "이름", example = "이름")
    val name: @NotBlank String,
    @field:Schema(description = "이메일", example = "example@gmail.com")
    val email: @NotBlank @Email String,
    @field:Schema(description = "주소", example = "서울특별시 강남구 xxx")
    val address: @NotBlank String,
    @field:Schema(description = "비밀번호", example = "pws123")
    val password: @NotBlank String,
)
