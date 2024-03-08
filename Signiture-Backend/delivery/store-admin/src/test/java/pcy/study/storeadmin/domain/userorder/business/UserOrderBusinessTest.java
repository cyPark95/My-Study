package pcy.study.storeadmin.domain.userorder.business;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import pcy.study.common.message.model.UserOrderMessage;
import pcy.study.db.storemenu.StoreMenu;
import pcy.study.db.userorder.UserOrder;
import pcy.study.db.userordermenu.UserOrderMenu;
import pcy.study.storeadmin.domain.message.connection.Connection;
import pcy.study.storeadmin.domain.message.connection.ConnectionPool;
import pcy.study.storeadmin.domain.storemenu.converter.StoreMenuConverter;
import pcy.study.storeadmin.domain.storemenu.service.StoreMenuService;
import pcy.study.storeadmin.domain.userorder.controller.model.UserOrderDetailResponse;
import pcy.study.storeadmin.domain.userorder.converter.UserOrderConverter;
import pcy.study.storeadmin.domain.userorder.service.UserOrderService;
import pcy.study.storeadmin.domain.userordermenu.service.UserOrderMenuService;

import java.util.List;

import static org.mockito.Mockito.*;
import static pcy.study.storeadmin.utility.StoreMenuUtils.STORE_MENU_ID;
import static pcy.study.storeadmin.utility.StoreMenuUtils.createStoreMenuWithId;
import static pcy.study.storeadmin.utility.StoreUtils.STORE_ID;
import static pcy.study.storeadmin.utility.UserOrderMenuUtils.createUserOrderMenuWithId;
import static pcy.study.storeadmin.utility.UserOrderUtils.USER_ORDER_ID;
import static pcy.study.storeadmin.utility.UserOrderUtils.createUserOrderWithId;

@ExtendWith(MockitoExtension.class)
class UserOrderBusinessTest {

    @InjectMocks
    private UserOrderBusiness userOrderBusiness;

    @Mock
    private UserOrderService userOrderService;
    @Spy
    private UserOrderConverter userOrderConverter;
    @Mock
    private UserOrderMenuService userOrderMenuService;
    @Mock
    private StoreMenuService storeMenuService;
    @Spy
    private StoreMenuConverter storeMenuConverter;
    @Mock
    private ConnectionPool<Long> connectionPool;

    @Test
    @DisplayName("사용자 주문 푸시")
    void pushUserOrder() {
        // given
        UserOrderMessage userOrderMessage = createUserOrderMessage();

        UserOrder userOrder = createUserOrderWithId();
        when(userOrderService.getUserOrderWithThrow(USER_ORDER_ID)).thenReturn(userOrder);
        UserOrderMenu userOrderMenu = createUserOrderMenuWithId();
        when(userOrderMenuService.getUserOrderMenus(USER_ORDER_ID)).thenReturn(List.of(userOrderMenu));
        StoreMenu storeMenu = createStoreMenuWithId();
        when(storeMenuService.getStoreMenuWithThrow(STORE_MENU_ID)).thenReturn(storeMenu);
        Connection fakeConnection = mock(Connection.class);
        when(connectionPool.getConnection(STORE_ID)).thenReturn(fakeConnection);

        // when
        userOrderBusiness.pushUserOrder(userOrderMessage);

        // then
        verify(fakeConnection, only()).sendMessage(any(String.class), any(UserOrderDetailResponse.class));
    }

    private UserOrderMessage createUserOrderMessage() {
        return UserOrderMessage.builder()
                .userOrderId(USER_ORDER_ID)
                .build();
    }
}
