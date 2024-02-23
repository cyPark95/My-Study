package pcy.study.api.domain.storemenu.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record StoreMenuRequest(
        @Schema(description = "가게 식별값", example = "1")
        @NotNull
        Long storeId,
        @Schema(description = "이름", example = "이름")
        @NotBlank
        String name,
        @Schema(description = "가격", example = "3000.0")
        @NotNull
        BigDecimal amount,
        @Schema(description = "썸네일 주소", example = "https://xxx")
        @NotBlank
        String thumbnailUrl
) {
}
