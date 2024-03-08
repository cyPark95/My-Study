package pcy.study.storeadmin.domain.userorder.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pcy.study.db.userorder.UserOrder;
import pcy.study.db.userorder.UserOrderRepository;
import pcy.study.storeadmin.config.annotation.ServiceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pcy.study.storeadmin.utility.UserOrderUtils.USER_ORDER_ID;
import static pcy.study.storeadmin.utility.UserOrderUtils.createUserOrder;

@ServiceTest
class UserOrderServiceTest {

    private UserOrderService userOrderService;

    @Autowired
    private UserOrderRepository userOrderRepository;

    @BeforeEach
    void setUp() {
        userOrderService = new UserOrderService(userOrderRepository);
    }

    @Test
    @DisplayName("사용자 주문 조회")
    void getUserOrder() {
        // given
        UserOrder userOrder = createUserOrder();
        userOrderRepository.save(userOrder);

        // when
        UserOrder result = userOrderService.getUserOrderWithThrow(USER_ORDER_ID);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("사용자 주문 조회 - 없는 ID")
    void getUserOrder_failureNotFound() {
        // when
        // then
        assertThatThrownBy(() -> userOrderService.getUserOrderWithThrow(-1L))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
