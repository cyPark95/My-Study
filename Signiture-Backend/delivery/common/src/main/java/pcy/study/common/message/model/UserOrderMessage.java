package pcy.study.common.message.model;

import lombok.Builder;

@Builder
public record UserOrderMessage(
        Long userOrderId
) {
}
