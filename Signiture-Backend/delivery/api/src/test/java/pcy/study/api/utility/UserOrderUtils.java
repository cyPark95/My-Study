package pcy.study.api.utility;

import org.springframework.test.util.ReflectionTestUtils;
import pcy.study.api.domain.userorder.controller.model.UserOrderDetailResponse;
import pcy.study.api.domain.userorder.controller.model.UserOrderRequest;
import pcy.study.api.domain.userorder.controller.model.UserOrderResponse;
import pcy.study.db.userorder.UserOrder;
import pcy.study.db.userorder.enums.UserOrderStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static pcy.study.api.utility.StoreMenuUtils.STORE_MENU_ID;
import static pcy.study.api.utility.StoreUtils.STORE_ID;
import static pcy.study.api.utility.StoreUtils.assertEqualsStoreResponse;
import static pcy.study.api.utility.UserUtils.USER_ID;

public class UserOrderUtils {

    public static final Long USER_ORDER_ID = 1L;
    public static final BigDecimal USER_ORDER_AMOUNT = BigDecimal.valueOf(10000.0);

    private static final Map<UserOrderStatus, Consumer<UserOrderResponse>> assertStatus = assertStatusInit();

    private UserOrderUtils() {
    }

    private static Map<UserOrderStatus, Consumer<UserOrderResponse>> assertStatusInit() {
        return Map.of(
                UserOrderStatus.REGISTERED, assertEqualsUserOrderStatusRegistered(),
                UserOrderStatus.ORDER, assertEqualsUserOrderStatusOrder(),
                UserOrderStatus.ACCEPT, assertEqualsUserOrderStatusAccept(),
                UserOrderStatus.COOKING, assertEqualsUserOrderStatusCooking(),
                UserOrderStatus.DELIVERY, assertEqualsUserOrderStatusDelivery(),
                UserOrderStatus.RECEIVE, assertEqualsUserOrderStatusReceive()
        );
    }

    public static UserOrder createUserOrder() {
        return UserOrder.builder()
                .userId(USER_ID)
                .storeId(STORE_ID)
                .amount(USER_ORDER_AMOUNT)
                .build();
    }

    public static UserOrder createUserOrderWithId() {
        UserOrder userOrder = createUserOrder();
        ReflectionTestUtils.setField(userOrder, "id", USER_ORDER_ID);
        return userOrder;
    }

    public static UserOrderRequest createUserOrderRequest() {
        return new UserOrderRequest(STORE_ID, List.of(STORE_MENU_ID));
    }

    public static void assertEqualsUserOrderDetailsResponse(UserOrderDetailResponse response) {
        assertAll(
                () -> assertEqualsUserOrderResponse(response.userOrder()),
                () -> assertEqualsStoreResponse(response.store()),
                () -> response.storeMenus().forEach(StoreMenuUtils::assertEqualsStoreMenuResponse)
        );
    }

    public static void assertEqualsUserOrderResponse(UserOrderResponse response) {
        assertAll(
                () -> assertThat(response.id()).isEqualTo(USER_ORDER_ID),
                () -> assertThat(response.amount()).isEqualTo(USER_ORDER_AMOUNT),
                () -> assertStatus.get(response.status()).accept(response)
        );
    }

    private static Consumer<UserOrderResponse> assertEqualsUserOrderStatusRegistered() {
        return (response) -> assertAll(
                () -> assertThat(response.status()).isEqualTo(UserOrderStatus.REGISTERED),
                () -> assertThat(response.orderedAt()).isNull(),
                () -> assertThat(response.acceptedAt()).isNull(),
                () -> assertThat(response.cookingStartedAt()).isNull(),
                () -> assertThat(response.deliveryStartedAt()).isNull(),
                () -> assertThat(response.receivedAt()).isNull()
        );
    }

    private static Consumer<UserOrderResponse> assertEqualsUserOrderStatusOrder() {
        return (response) -> assertAll(
                () -> assertThat(response.status()).isEqualTo(UserOrderStatus.ORDER),
                () -> assertThat(response.orderedAt()).isNotNull()
        );
    }

    private static Consumer<UserOrderResponse> assertEqualsUserOrderStatusAccept() {
        return (response) -> assertAll(
                () -> assertThat(response.status()).isEqualTo(UserOrderStatus.ACCEPT),
                () -> assertThat(response.acceptedAt()).isNotNull()
        );
    }

    private static Consumer<UserOrderResponse> assertEqualsUserOrderStatusCooking() {
        return (response) -> assertAll(
                () -> assertThat(response.status()).isEqualTo(UserOrderStatus.COOKING),
                () -> assertThat(response.cookingStartedAt()).isNotNull()
        );
    }

    private static Consumer<UserOrderResponse> assertEqualsUserOrderStatusDelivery() {
        return (response) -> assertAll(
                () -> assertThat(response.status()).isEqualTo(UserOrderStatus.DELIVERY),
                () -> assertThat(response.deliveryStartedAt()).isNotNull()
        );
    }

    private static Consumer<UserOrderResponse> assertEqualsUserOrderStatusReceive() {
        return (response) -> assertAll(
                () -> assertThat(response.status()).isEqualTo(UserOrderStatus.RECEIVE),
                () -> assertThat(response.receivedAt()).isNotNull()
        );
    }
}
