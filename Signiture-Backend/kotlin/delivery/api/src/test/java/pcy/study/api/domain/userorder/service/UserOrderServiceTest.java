package pcy.study.api.domain.userorder.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pcy.study.common.exception.ApiException;
import pcy.study.api.config.annotation.ServiceTest;
import pcy.study.db.userorder.UserOrder;
import pcy.study.db.userorder.UserOrderRepository;
import pcy.study.db.userorder.enums.UserOrderStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pcy.study.api.utility.UserOrderUtils.createUserOrder;
import static pcy.study.api.utility.UserUtils.USER_ID;

@ServiceTest
class UserOrderServiceTest {

    private UserOrderService userOrderService;

    @Autowired
    private UserOrderRepository userOrderRepository;

    private UserOrder userOrder;

    @BeforeEach
    void setUp() {
        userOrderService = new UserOrderService(userOrderRepository);

        // given
        userOrder = saveUserOrder();
    }

    @Test
    @DisplayName("주문 조회")
    void getUserOrderWithOutStatusWithThrow() {
        // when
        UserOrder result = userOrderService.getUserOrderWithOutStatusWithThrow(userOrder.getId(), USER_ID);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("주문 조회 - 없는 ID")
    void getUserOrderWithOutStatusWithThrow_failureNotFoundId() {
        // when
        // then
        assertThatThrownBy(() -> userOrderService.getUserOrderWithOutStatusWithThrow(-1L, USER_ID))
                .isInstanceOf(ApiException.class);
    }

    @Test
    @DisplayName("주문 조회 - 없는 사용자 ID")
    void getUserOrderWithOutStatusWithThrow_failureNotFoundUserId() {
        // when
        // then
        assertThatThrownBy(() -> userOrderService.getUserOrderWithOutStatusWithThrow(userOrder.getId(), -1L))
                .isInstanceOf(ApiException.class);
    }

    @Test
    @DisplayName("사용자 주문 조회")
    void searchByUser() {
        // when
        List<UserOrder> result = userOrderService.searchByUser(USER_ID);

        // then
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("진행중인 주문 조회")
    void current() {
        // given
        UserOrder order = createUserOrder();
        order.order();
        userOrderRepository.save(order);

        UserOrder accept = createUserOrder();
        accept.accept();
        userOrderRepository.save(accept);

        UserOrder cooking = createUserOrder();
        cooking.cooking();
        userOrderRepository.save(cooking);

        UserOrder delivery = createUserOrder();
        delivery.delivery();
        userOrderRepository.save(delivery);

        // when
        List<UserOrder> result = userOrderService.current(USER_ID);

        // then
        assertThat(result.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("완료된 주문 조회")
    void history() {
        // given
        UserOrder receive = createUserOrder();
        receive.receive();
        userOrderRepository.save(receive);

        // when
        List<UserOrder> result = userOrderService.history(USER_ID);

        // then
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("주문 상태 변경")
    void order() {
        // when
        UserOrder result = userOrderService.order(userOrder);

        // then
        assertThat(result.getStatus()).isEqualTo(UserOrderStatus.ORDER);
    }

    @Test
    @DisplayName("확인 상태 변경")
    void accept() {
        // when
        UserOrder result = userOrderService.accept(userOrder);

        // then
        assertThat(result.getStatus()).isEqualTo(UserOrderStatus.ACCEPT);
    }

    @Test
    @DisplayName("요리중 상태 변경")
    void cooking() {
        // when
        UserOrder result = userOrderService.cooking(userOrder);

        // then
        assertThat(result.getStatus()).isEqualTo(UserOrderStatus.COOKING);
    }

    @Test
    @DisplayName("배달중 상태 변경")
    void delivery() {
        // when
        UserOrder result = userOrderService.delivery(userOrder);

        // then
        assertThat(result.getStatus()).isEqualTo(UserOrderStatus.DELIVERY);
    }

    @Test
    @DisplayName("완료 상태 변경")
    void receive() {
        // when
        UserOrder result = userOrderService.receive(userOrder);

        // then
        assertThat(result.getStatus()).isEqualTo(UserOrderStatus.RECEIVE);
    }

    private UserOrder saveUserOrder() {
        UserOrder userOrder = createUserOrder();
        userOrderRepository.save(userOrder);
        return userOrder;
    }
}
