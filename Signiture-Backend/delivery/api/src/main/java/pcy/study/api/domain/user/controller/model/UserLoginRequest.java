package pcy.study.api.domain.user.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record UserLoginRequest(
        @Schema(description = "이메일", example = "example@gmail.com")
        @NotBlank
        String email,
        @Schema(description = "비밀번호", example = "pws123")
        @NotBlank
        String password
) {
}
