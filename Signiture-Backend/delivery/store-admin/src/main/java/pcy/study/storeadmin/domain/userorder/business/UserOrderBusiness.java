package pcy.study.storeadmin.domain.userorder.business;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pcy.study.common.message.model.UserOrderMessage;
import pcy.study.storeadmin.domain.sse.connection.ConnectionPool;
import pcy.study.storeadmin.domain.sse.connection.model.UserSseConnection;
import pcy.study.storeadmin.domain.storemenu.converter.StoreMenuConverter;
import pcy.study.storeadmin.domain.storemenu.service.StoreMenuService;
import pcy.study.storeadmin.domain.userorder.controller.model.UserOrderDetailResponse;
import pcy.study.storeadmin.domain.userorder.converter.UserOrderConverter;
import pcy.study.storeadmin.domain.userorder.service.UserOrderService;
import pcy.study.storeadmin.domain.userordermenu.service.UserOrderMenuService;

@Service
@RequiredArgsConstructor
public class UserOrderBusiness {

    private final UserOrderService userOrderService;
    private final UserOrderConverter userOrderConverter;
    private final UserOrderMenuService userOrderMenuService;
    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;
    private final ConnectionPool<Long, UserSseConnection> connectionPool;

    public void pushUserOrder(UserOrderMessage userOrderMessage) {
        var userOrder = userOrderService.getUserOrder(userOrderMessage.userOrderId())
                .orElseThrow(() -> new IllegalArgumentException(String.format("ID: [%d] UserOrder Not Found", userOrderMessage.userOrderId())));
        var userOrderMenus = userOrderMenuService.getUserOrderMenus(userOrder.getId());
        var storeMenus = userOrderMenus.stream()
                .map(it -> storeMenuService.getStoreMenuWithThrow(it.getStoreMenuId()))
                .toList();
        var userOrderResponse = userOrderConverter.toResponse(userOrder);
        var storeMenuResponse = storeMenuConverter.toResponse(storeMenus);
        var response = UserOrderDetailResponse.builder()
                .userOrder(userOrderResponse)
                .storeMenus(storeMenuResponse)
                .build();
        var userConnection = connectionPool.getSession(userOrder.getStoreId());
        userConnection.sendMessage(response);
    }
}
