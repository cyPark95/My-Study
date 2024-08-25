package pcy.study.storeadmin.domain.storemenu.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record StoreMenuResponse(
        @Schema(description = "메뉴 식별값", example = "1")
        Long id,
        @Schema(description = "이름", example = "이름")
        String name,
        @Schema(description = "메뉴 가격", example = "3000.0")
        BigDecimal amount,
        @Schema(description = "썸네일 주소", example = "https://xxx")
        String thumbnailUrl
) {
}
