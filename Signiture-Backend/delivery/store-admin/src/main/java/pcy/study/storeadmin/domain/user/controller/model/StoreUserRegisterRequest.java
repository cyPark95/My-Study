package pcy.study.storeadmin.domain.user.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import pcy.study.db.storeuser.enums.StoreUserRole;

public record StoreUserRegisterRequest(
        @Schema(description = "가게 이름", example = "이름")
        @NotBlank
        String storeName,
        @Schema(description = "이메일", example = "example@gmail.com")
        @NotBlank
        @Email
        String email,
        @Schema(description = "비밀번호", example = "pws123")
        @NotBlank
        String password,
        @Schema(description = "역할", example = "USER")
        @NotNull
        StoreUserRole role
) {
}
