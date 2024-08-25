package pcy.study.storeadmin.domain.userorder.controller.model;

import lombok.Builder;
import pcy.study.storeadmin.domain.storemenu.controller.model.StoreMenuResponse;

import java.util.List;

@Builder
public record UserOrderDetailResponse(
        UserOrderResponse userOrder,
        List<StoreMenuResponse> storeMenus
) {
}
