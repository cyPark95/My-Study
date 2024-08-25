package pcy.study.api.domain.storemenu.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record StoreMenuResponse(
        @Schema(description = "식별값", example = "1")
        Long id,
        @Schema(description = "가게 식별값", example = "1")
        Long storeId,
        @Schema(description = "이름", example = "이름")
        String name,
        @Schema(description = "가격", example = "8000.0")
        BigDecimal amount,
        @Schema(description = "썸네일 주소", example = "https://xxx")
        String thumbnailUrl,
        @Schema(description = "좋아요 수", example = "7")
        int likeCount,
        @Schema(description = "순서", example = "1")
        int sequence
) {
}
