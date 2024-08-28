package pcy.study.api.domain.userorder.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pcy.study.common.api.code.ErrorCode;
import pcy.study.common.api.code.UserOrderErrorCode;
import pcy.study.common.exception.ApiException;
import pcy.study.db.userorder.UserOrder;
import pcy.study.db.userorder.UserOrderRepository;
import pcy.study.db.userorder.enums.UserOrderStatus;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserOrderService {

    private final UserOrderRepository userOrderRepository;

    public UserOrder getUserOrderWithOutStatusWithThrow(Long id, Long userId) {
        return userOrderRepository.findFirstByIdAndUserId(id, userId)
                .orElseThrow(() -> new ApiException(
                        UserOrderErrorCode.USER_ORDER_NOT_FOUND,
                        String.format("ID: [%d], User ID: [%d] UserOrder Not Found", id, userId)
                ));
    }

    public List<UserOrder> searchByUser(Long userId) {
        return userOrderRepository.findAllByUserIdAndStatusOrderByIdDesc(userId, UserOrderStatus.REGISTERED);
    }

    public List<UserOrder> current(Long userId) {
        return searchByUser(
                userId,
                List.of(
                        UserOrderStatus.ORDER,
                        UserOrderStatus.ACCEPT,
                        UserOrderStatus.COOKING,
                        UserOrderStatus.DELIVERY
                )
        );
    }

    public List<UserOrder> history(Long userId) {
        return searchByUser(userId, List.of(UserOrderStatus.RECEIVE));
    }

    private List<UserOrder> searchByUser(Long userId, List<UserOrderStatus> statuses) {
        return userOrderRepository.findAllByUserIdAndStatusInOrderByIdDesc(userId, statuses);
    }

    public UserOrder order(UserOrder userOrder) {
        return changeStatus(userOrder, userOrder::order);
    }

    public UserOrder accept(UserOrder userOrder) {
        return changeStatus(userOrder, userOrder::accept);
    }

    public UserOrder cooking(UserOrder userOrder) {
        return changeStatus(userOrder, userOrder::cooking);
    }

    public UserOrder delivery(UserOrder userOrder) {
        return changeStatus(userOrder, userOrder::delivery);
    }

    public UserOrder receive(UserOrder userOrder) {
        return changeStatus(userOrder, userOrder::receive);
    }

    private UserOrder changeStatus(UserOrder userOrder, Runnable runnable) {
        return Optional.ofNullable(userOrder)
                .map(it -> {
                    runnable.run();
                    return userOrderRepository.save(userOrder);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserOrder Entity is Null"));
    }
}
