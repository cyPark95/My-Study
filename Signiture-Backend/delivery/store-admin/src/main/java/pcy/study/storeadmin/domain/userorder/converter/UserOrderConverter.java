package pcy.study.storeadmin.domain.userorder.converter;

import org.springframework.stereotype.Service;
import pcy.study.db.userorder.UserOrder;
import pcy.study.storeadmin.domain.userorder.controller.model.UserOrderResponse;

import java.util.Optional;

@Service
public class UserOrderConverter {

    public UserOrderResponse toResponse(UserOrder userOrder) {
        return Optional.ofNullable(userOrder)
                .map(it -> UserOrderResponse.builder()
                        .id(userOrder.getId())
                        .userId(userOrder.getUserId())
                        .storeId(userOrder.getStoreId())
                        .status(userOrder.getStatus())
                        .amount(userOrder.getAmount())
                        .orderedAt(userOrder.getOrderedAt())
                        .acceptedAt(userOrder.getAcceptedAt())
                        .cookingStartedAt(userOrder.getCookingStartedAt())
                        .deliveryStartedAt(userOrder.getDeliveryStartedAt())
                        .receivedAt(userOrder.getReceivedAt())
                        .build())
                .orElseThrow(() -> new IllegalArgumentException("UserOrder Entity is Null"));
    }
}
