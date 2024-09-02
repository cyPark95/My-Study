package pcy.study.api.domain.user.model

import io.swagger.v3.oas.annotations.media.Schema

data class UserDetails(
    @field:Schema(description = "식별값", example = "1")
    val id:Long,
    @field:Schema(description = "이름", example = "이름")
    val name:String,
    @field:Schema(description = "이메일", example = "example@gmail.com")
    val email:String,
    @field:Schema(description = "주소", example = "서울특별시 강남구 xxx")
    val address:String,
)