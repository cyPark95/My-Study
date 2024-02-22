package pcy.study.api.domain.store.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import pcy.study.db.store.enums.StoreCategory;

import java.math.BigDecimal;

public record StoreRegisterRequest(
        @Schema(description = "이름", example = "이름")
        @NotBlank
        String name,
        @Schema(description = "주소", example = "서울특별시 강남구 xxx")
        @NotBlank
        String address,
        @Schema(description = "카테고리", example = "KOREAN_FOOD")
        @NotNull
        StoreCategory category,
        @Schema(description = "썸네일 주소", example = "https://xxx")
        @NotBlank
        String thumbnailUrl,
        @Schema(description = "최소 주문금액", example = "10000.0")
        @NotNull
        BigDecimal minimumAmount,
        @Schema(description = "최소 배달금액", example = "3000.0")
        @NotNull
        BigDecimal minimumDeliveryAmount,
        @Schema(description = "전화번호", example = "010-0000-0000")
        String phoneNumber
) {
}
