package pcy.study.api.domain.userorder.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import pcy.study.api.domain.store.controller.model.StoreResponse;
import pcy.study.api.domain.storemenu.controller.model.StoreMenuResponse;

import java.util.List;

@Builder
public record UserOrderDetailResponse(
        @Schema(description = "주문 정보")
        UserOrderResponse userOrder,
        @Schema(description = "가게 정보")
        StoreResponse store,
        @Schema(description = "메뉴 목록")
        List<StoreMenuResponse> storeMenus
) {
}
