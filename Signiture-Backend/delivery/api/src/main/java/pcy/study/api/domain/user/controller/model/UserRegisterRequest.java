package pcy.study.api.domain.user.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRegisterRequest(
        @Schema(description = "이름", example = "이름")
        @NotBlank
        String name,
        @Schema(description = "이메일", example = "example@gmail.com")
        @NotBlank
        @Email
        String email,
        @Schema(description = "주소", example = "서울특별시 강남구 xxx")
        @NotBlank
        String address,
        @Schema(description = "비밀번호", example = "pws123")
        @NotBlank
        String password
) {
}
