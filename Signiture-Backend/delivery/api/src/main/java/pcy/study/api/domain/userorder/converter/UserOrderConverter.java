package pcy.study.api.domain.userorder.converter;

import pcy.study.api.common.annotation.Converter;
import pcy.study.api.common.api.error.ErrorCode;
import pcy.study.api.common.exception.ApiException;
import pcy.study.api.domain.user.model.UserDetails;
import pcy.study.api.domain.userorder.controller.model.UserOrderResponse;
import pcy.study.db.storemenu.StoreMenu;
import pcy.study.db.userorder.UserOrder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Converter
public class UserOrderConverter {

    public UserOrder toEntity(UserDetails userDetails, Long storeId, List<StoreMenu> storeMenus) {
        return Optional.ofNullable(storeMenus)
                .map(it -> UserOrder.builder()
                        .userId(userDetails.id())
                        .storeId(storeId)
                        .amount(getTotalAmount(storeMenus))
                        .build())
                .orElseThrow(() -> new ApiException("StoreMenus is Null", ErrorCode.NULL_POINT));
    }

    private BigDecimal getTotalAmount(List<StoreMenu> storeMenus) {
        return storeMenus.stream()
                .map(StoreMenu::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public UserOrderResponse toResponse(UserOrder userOrder) {
        return Optional.ofNullable(userOrder)
                .map(it -> UserOrderResponse.builder()
                        .id(userOrder.getId())
                        .status(userOrder.getStatus())
                        .amount(userOrder.getAmount())
                        .orderedAt(userOrder.getOrderedAt())
                        .acceptedAt(userOrder.getAcceptedAt())
                        .cookingStartedAt(userOrder.getCookingStartedAt())
                        .deliveryStartedAt(userOrder.getDeliveryStartedAt())
                        .receivedAt(userOrder.getReceivedAt())
                        .build())
                .orElseThrow(() -> new ApiException("UserOrder is Null", ErrorCode.NULL_POINT));
    }
}
