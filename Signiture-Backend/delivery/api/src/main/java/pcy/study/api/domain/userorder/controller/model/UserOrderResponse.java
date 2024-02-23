package pcy.study.api.domain.userorder.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import pcy.study.db.userorder.enums.UserOrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record UserOrderResponse(
        @Schema(description = "식별값", example = "1")
        Long id,
        @Schema(description = "주문 상태", example = "ORDER")
        UserOrderStatus status,
        @Schema(description = "식별값", example = "1")
        BigDecimal amount,
        @Schema(description = "주문 시간", example = "2024-02-23T12:30:15.00.000Z")
        LocalDateTime orderedAt,
        @Schema(description = "확인 시간", example = "2024-02-23T12:30:15.00.000Z")
        LocalDateTime acceptedAt,
        @Schema(description = "요리 시작 시간", example = "2024-02-23T12:30:15.00.000Z")
        LocalDateTime cookingStartedAt,
        @Schema(description = "배달 시작 시간", example = "2024-02-23T12:30:15.00.000Z")
        LocalDateTime deliveryStartedAt,
        @Schema(description = "완료 시간", example = "2024-02-23T12:30:15.00.000Z")
        LocalDateTime receivedAt
) {
}
