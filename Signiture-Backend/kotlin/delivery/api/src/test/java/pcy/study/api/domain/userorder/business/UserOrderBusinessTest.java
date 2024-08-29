package pcy.study.api.domain.userorder.business;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import pcy.study.api.domain.store.converter.StoreConverter;
import pcy.study.api.domain.store.service.StoreService;
import pcy.study.api.domain.storemenu.converter.StoreMenuConverter;
import pcy.study.api.domain.storemenu.service.StoreMenuService;
import pcy.study.api.domain.user.model.UserDetails;
import pcy.study.api.domain.userorder.controller.model.UserOrderDetailResponse;
import pcy.study.api.domain.userorder.controller.model.UserOrderRequest;
import pcy.study.api.domain.userorder.controller.model.UserOrderResponse;
import pcy.study.api.domain.userorder.converter.UserOrderConverter;
import pcy.study.api.domain.userorder.producer.UserOrderProducer;
import pcy.study.api.domain.userorder.service.UserOrderService;
import pcy.study.api.domain.userordermenu.convertor.UserOrderMenuConverter;
import pcy.study.api.domain.userordermenu.service.UserOrderMenuService;
import pcy.study.db.store.Store;
import pcy.study.db.storemenu.StoreMenu;
import pcy.study.db.userorder.UserOrder;
import pcy.study.db.userordermenu.UserOrderMenu;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static pcy.study.api.utility.StoreMenuUtils.STORE_MENU_ID;
import static pcy.study.api.utility.StoreMenuUtils.createStoreMenuWithId;
import static pcy.study.api.utility.StoreUtils.STORE_ID;
import static pcy.study.api.utility.StoreUtils.createStoreWithId;
import static pcy.study.api.utility.UserOrderMenuUtils.createUserOrderMenuWithId;
import static pcy.study.api.utility.UserOrderUtils.*;
import static pcy.study.api.utility.UserUtils.createUserDetails;

@ExtendWith(MockitoExtension.class)
class UserOrderBusinessTest {

    @InjectMocks
    private UserOrderBusiness userOrderBusiness;

    @Mock
    private UserOrderService userOrderService;
    @Spy
    private UserOrderConverter userOrderConverter;
    @Mock
    private StoreService storeService;
    @Spy
    private StoreConverter storeConverter;
    @Mock
    private StoreMenuService storeMenuService;
    @Spy
    private StoreMenuConverter storeMenuConverter;
    @Mock
    private UserOrderMenuService userOrderMenuService;
    @Spy
    private UserOrderMenuConverter userOrderMenuConverter;
    @Mock
    private UserOrderProducer userOrderProducer;

    @Test
    @DisplayName("메뉴 주문")
    void userOrder() {
        // given
        UserDetails userDetails = createUserDetails();
        UserOrderRequest request = createUserOrderRequest();

        StoreMenu storeMenu = createStoreMenuWithId();
        when(storeMenuService.getStoreMenuWithThrow(STORE_MENU_ID)).thenReturn(storeMenu);
        UserOrder userOrder = createUserOrderWithId();
        userOrder.order();
        when(userOrderService.order(any(UserOrder.class))).thenReturn(userOrder);

        // when
        UserOrderResponse result = userOrderBusiness.userOrder(userDetails, request);

        // then
        assertEqualsUserOrderResponse(result);
    }

    @Test
    @DisplayName("현재 주문 조회")
    void current() {
        // given
        UserDetails userDetails = createUserDetails();

        UserOrder order = createUserOrderWithId();
        order.order();
        UserOrder accept = createUserOrderWithId();
        accept.accept();
        UserOrder cooking = createUserOrderWithId();
        cooking.cooking();
        UserOrder delivery = createUserOrderWithId();
        delivery.delivery();
        when(userOrderService.current(userDetails.id())).thenReturn(List.of(
                order, accept, cooking, delivery
        ));
        mockToSuerOrderDetailResponse();

        // when
        List<UserOrderDetailResponse> results = userOrderBusiness.current(userDetails);

        // then
        assertThat(results.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("과거 주문 조회")
    void history() {
        // given
        UserDetails userDetails = createUserDetails();

        UserOrder receive = createUserOrderWithId();
        receive.receive();
        when(userOrderService.history(userDetails.id())).thenReturn(List.of(receive));
        mockToSuerOrderDetailResponse();

        // when
        List<UserOrderDetailResponse> results = userOrderBusiness.history(userDetails);

        // then
        assertThat(results.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("주문 조회")
    void read() {
        // given
        UserDetails userDetails = createUserDetails();

        UserOrder userOrder = createUserOrderWithId();
        when(userOrderService.getUserOrderWithOutStatusWithThrow(userOrder.getId(), userDetails.id())).thenReturn(userOrder);
        mockToSuerOrderDetailResponse();

        // when
        UserOrderDetailResponse result = userOrderBusiness.read(userDetails, userOrder.getId());

        // then
        assertEqualsUserOrderDetailsResponse(result);
    }

    private void mockToSuerOrderDetailResponse() {
        UserOrder userOrderWithId = createUserOrderWithId();
        UserOrderMenu userOrderMenu = createUserOrderMenuWithId();
        when(userOrderMenuService.searchByUserOrder(userOrderWithId)).thenReturn(List.of(userOrderMenu));
        StoreMenu storeMenu = createStoreMenuWithId();
        when(storeMenuService.getStoreMenuWithThrow(STORE_MENU_ID)).thenReturn(storeMenu);
        Store store = createStoreWithId();
        when(storeService.getStoreWithThrow(STORE_ID)).thenReturn(store);
    }
}
