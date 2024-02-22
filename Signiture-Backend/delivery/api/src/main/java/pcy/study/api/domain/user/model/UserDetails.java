package pcy.study.api.domain.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record UserDetails(
        @Schema(description = "식별값", example = "1L")
        Long id,
        @Schema(description = "이름", example = "이름")
        String name,
        @Schema(description = "이메일", example = "example@gmail.com")
        String email,
        @Schema(description = "주소", example = "서울특별시 강남구 xxx")
        String address
) {
}
