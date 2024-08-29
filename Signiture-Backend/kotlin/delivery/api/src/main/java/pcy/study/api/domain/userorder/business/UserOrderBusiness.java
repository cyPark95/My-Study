package pcy.study.api.domain.userorder.business;

import lombok.RequiredArgsConstructor;
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
import pcy.study.common.annotation.Business;
import pcy.study.db.storemenu.StoreMenu;
import pcy.study.db.userorder.UserOrder;
import pcy.study.db.userordermenu.UserOrderMenu;

import java.util.List;

@Business
@RequiredArgsConstructor
public class UserOrderBusiness {

    private final UserOrderService userOrderService;
    private final UserOrderConverter userOrderConverter;
    private final StoreService storeService;
    private final StoreConverter storeConverter;
    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;
    private final UserOrderMenuService userOrderMenuService;
    private final UserOrderMenuConverter userOrderMenuConverter;
    private final UserOrderProducer userOrderProducer;

    public UserOrderResponse userOrder(UserDetails userDetails, UserOrderRequest request) {
        var store = storeService.getStoreWithThrow(request.storeId());
        var storeMenus = getStoreMenus(request.storeMenuIds());
        var userOrder = userOrderConverter.toEntity(userDetails, store, storeMenus);
        var newUserOrder = userOrderService.order(userOrder);
        var userOrderMenus = getOrderMenus(storeMenus, userOrder);
        userOrderMenus.forEach(userOrderMenuService::order);
        userOrderProducer.sendOrder(newUserOrder);
        return userOrderConverter.toResponse(newUserOrder);
    }

    private List<StoreMenu> getStoreMenus(List<Long> storeMenuIds) {
        return storeMenuIds.stream()
                .map(storeMenuService::getStoreMenuWithThrow)
                .toList();
    }

    private List<UserOrderMenu> getOrderMenus(List<StoreMenu> storeMenuEntities, UserOrder userOrderEntity) {
        return storeMenuEntities.stream()
                .map(it -> userOrderMenuConverter.toEntity(userOrderEntity, it))
                .toList();
    }

    public List<UserOrderDetailResponse> current(UserDetails userDetails) {
        var userOrders = userOrderService.current(userDetails.id());
        return userOrders.stream()
                .map(this::toUserOrderDetailResponse)
                .toList();
    }

    public List<UserOrderDetailResponse> history(UserDetails userDetails) {
        var userOrders = userOrderService.history(userDetails.id());
        return userOrders.stream()
                .map(this::toUserOrderDetailResponse)
                .toList();
    }

    public UserOrderDetailResponse read(UserDetails userDetails, Long id) {
        var userOrder = userOrderService.getUserOrderWithOutStatusWithThrow(id, userDetails.id());
        return toUserOrderDetailResponse(userOrder);
    }

    private UserOrderDetailResponse toUserOrderDetailResponse(UserOrder userOrder) {
        var userOrderMenus = userOrderMenuService.searchByUserOrder(userOrder);
        var storeMenus = userOrderMenus.stream()
                .map(userOrderMenu -> storeMenuService.getStoreMenuWithThrow(userOrderMenu.getStoreMenu().getId()))
                .toList();
        // TODO 리팩토링
        var store = storeService.getStoreWithThrow(storeMenus.stream().findFirst().get().getStore().getId());
        return UserOrderDetailResponse.builder()
                .userOrder(userOrderConverter.toResponse(userOrder))
                .store(storeConverter.toResponse(store))
                .storeMenus(storeMenuConverter.toResponse(storeMenus))
                .build();
    }
}
