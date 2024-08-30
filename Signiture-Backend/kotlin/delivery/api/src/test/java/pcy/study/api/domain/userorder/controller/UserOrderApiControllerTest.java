package pcy.study.api.domain.userorder.controller;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;
import pcy.study.api.common.ApiTest;
import pcy.study.api.domain.userorder.controller.model.UserOrderDetailResponse;
import pcy.study.api.domain.userorder.controller.model.UserOrderRequest;
import pcy.study.api.domain.userorder.controller.model.UserOrderResponse;
import pcy.study.db.store.StoreRepository;
import pcy.study.db.storemenu.StoreMenu;
import pcy.study.db.storemenu.StoreMenuRepository;
import pcy.study.db.userorder.UserOrder;
import pcy.study.db.userorder.UserOrderRepository;
import pcy.study.db.userordermenu.UserOrderMenu;
import pcy.study.db.userordermenu.UserOrderMenuRepository;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static pcy.study.api.utility.StoreMenuUtils.*;
import static pcy.study.api.utility.StoreUtils.STORE_ID;
import static pcy.study.api.utility.StoreUtils.createStore;
import static pcy.study.api.utility.UserOrderUtils.*;

class UserOrderApiControllerTest extends ApiTest {

    @Autowired
    private UserOrderRepository userOrderRepository;

    @Autowired
    private UserOrderMenuRepository userOrderMenuRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreMenuRepository storeMenuRepository;

    @BeforeEach
    void setUp() {
        // given
        storeRepository.save(createStore());
        storeMenuRepository.save(createStoreMenu());
    }

    @Test
    @DisplayName("메뉴 주문")
    void userOrder() {
        // given
        UserOrderRequest request = createUserOrderRequest();
        String url = "/api/user-orders";

        // when
        ExtractableResponse<Response> response = post(request, url);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        UserOrderResponse result = response.jsonPath().getObject("body", UserOrderResponse.class);
        assertEqualsUserOrderResponse(result);
    }

    @ParameterizedTest
    @MethodSource("invalidUserOrderParameter")
    @DisplayName("메뉴 주문 - 잘못된 파라미터")
    void userOrder_invalidParameter(UserOrderRequest request) {
        // given
        String url = "/api/user-orders";

        // when
        ExtractableResponse<Response> response = post(request, url);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    private static Stream<UserOrderRequest> invalidUserOrderParameter() {
        return Stream.of(
                new UserOrderRequest(null, List.of(STORE_MENU_ID)),
                new UserOrderRequest(STORE_ID, null)
        );
    }

    @Test
    @DisplayName("현재 주문 조회")
    void current() {
        // given
        saveAllUserOrderStatus();

        String url = "/api/user-orders/current";

        // when
        ExtractableResponse<Response> response = get(url);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        List<UserOrderDetailResponse> results = response.jsonPath().getList("body", UserOrderDetailResponse.class);
        assertThat(results.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("과거 주문 조회")
    void history() {
        // given
        saveAllUserOrderStatus();

        String url = "/api/user-orders/history";

        // when
        ExtractableResponse<Response> response = get(url);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        List<UserOrderDetailResponse> results = response.jsonPath().getList("body", UserOrderDetailResponse.class);
        assertThat(results.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("주문 조회")
    void read() {
        // given
        UserOrder userOrder = createUserOrder();
        saveUserOrder(userOrder);

        String url = "/api/user-orders/" + USER_ORDER_ID;

        // when
        ExtractableResponse<Response> response = get(url);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        UserOrderDetailResponse result = response.jsonPath().getObject("body", UserOrderDetailResponse.class);
        assertEqualsUserOrderDetailsResponse(result);
    }

    private void saveAllUserOrderStatus() {
        UserOrder order = createUserOrder();
        order.order();
        saveUserOrder(order);
        UserOrder accept = createUserOrder();
        accept.accept();
        saveUserOrder(accept);
        UserOrder cooking = createUserOrder();
        cooking.cooking();
        saveUserOrder(cooking);
        UserOrder delivery = createUserOrder();
        delivery.delivery();
        saveUserOrder(delivery);
        UserOrder receive = createUserOrder();
        receive.receive();
        saveUserOrder(receive);
    }

    private void saveUserOrder(UserOrder userOrder) {
        userOrderRepository.save(userOrder);
        saveUserOrderMenu(userOrder.getId());
    }

    private void saveUserOrderMenu(Long userOrderId) {
        UserOrder userOrder = createUserOrder();
        ReflectionTestUtils.setField(userOrder, "id", userOrderId);
        StoreMenu storeMenu = createStoreMenuWithId();
        userOrderMenuRepository.save(new UserOrderMenu(userOrder, storeMenu));
    }
}
