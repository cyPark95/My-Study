package pcy.study.storeadmin.domain.user.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import pcy.study.db.storeuser.enums.StoreUserRole;

@Builder
public record UserResponse(
        @Schema(description = "사용자 식별값", example = "1")
        Long id,
        @Schema(description = "이메일", example = "example@gmail.com")
        String email,
        @Schema(description = "역할", example = "USER")
        StoreUserRole role
) {
}
