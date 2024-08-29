package pcy.study.storeadmin.domain.userorder.business;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pcy.study.common.message.model.UserOrderMessage;
import pcy.study.db.storemenu.StoreMenu;
import pcy.study.db.userordermenu.UserOrderMenu;
import pcy.study.storeadmin.domain.sse.connection.ConnectionPool;
import pcy.study.storeadmin.domain.storemenu.converter.StoreMenuConverter;
import pcy.study.storeadmin.domain.storemenu.service.StoreMenuService;
import pcy.study.storeadmin.domain.userorder.converter.UserOrderConverter;
import pcy.study.storeadmin.domain.userorder.service.UserOrderService;
import pcy.study.storeadmin.domain.userordermenu.service.UserOrderMenuService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserOrderBusiness {

    private final UserOrderService userOrderService;
    private final UserOrderConverter userOrderConverter;
    private final UserOrderMenuService userOrderMenuService;
    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;
    private final ConnectionPool<Long> connectionPool;

    public void pushUserOrder(UserOrderMessage userOrderMessage) {
        var userOrder = userOrderService.getUserOrderWithThrow(userOrderMessage.userOrderId());
        var userOrderResponse = userOrderConverter.toResponse(userOrder);
        var userOrderMenus = userOrderMenuService.getUserOrderMenus(userOrder);
        var storeMenus = getStoreMenus(userOrderMenus);
        var storeMenuResponse = storeMenuConverter.toResponse(storeMenus);
        var userOrderDetailresponse = userOrderConverter.toDetailResponse(userOrderResponse, storeMenuResponse);
        var userConnection = connectionPool.getConnection(userOrder.getStore().getId());
        userConnection.sendMessage("push", userOrderDetailresponse);
    }

    private List<StoreMenu> getStoreMenus(List<UserOrderMenu> userOrderMenus) {
        return userOrderMenus.stream()
                .map(it -> storeMenuService.getStoreMenuWithThrow(it.getStoreMenu().getId()))
                .toList();
    }
}
