package pcy.study.api.domain.userorder.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UserOrderRequest(
        @Schema(description = "가게 식별값", example = "1")
        @NotNull
        Long storeId,
        @Schema(description = "메뉴 식별값 목록", example = "[1, 2]")
        @NotNull
        List<Long> storeMenuIds
) {
}
