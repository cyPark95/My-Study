package pcy.study.api.domain.store.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import pcy.study.db.store.enums.StoreCategory;

import java.math.BigDecimal;

@Builder
public record StoreResponse(
        @Schema(description = "식별값", example = "1")
        Long id,
        @Schema(description = "이름", example = "이름")
        String name,
        @Schema(description = "주소", example = "서울특별시 강남구 xxx")
        String address,
        @Schema(description = "카테고리", example = "한식")
        StoreCategory category,
        @Schema(description = "별점", example = "4.7")
        double star,
        @Schema(description = "썸네일 주소", example = "https://xxx")
        String thumbnailUrl,
        @Schema(description = "최소 주문금액", example = "10000.0")
        BigDecimal minimumAmount,
        @Schema(description = "최소 배달금액", example = "3000.0")
        BigDecimal minimumDeliveryAmount,
        @Schema(description = "전화번호", example = "010-0000-0000")
        String phoneNumber
) {
}
