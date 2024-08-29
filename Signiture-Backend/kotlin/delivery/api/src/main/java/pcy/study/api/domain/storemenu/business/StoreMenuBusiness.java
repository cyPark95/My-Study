package pcy.study.api.domain.storemenu.business;

import lombok.RequiredArgsConstructor;
import pcy.study.api.domain.store.service.StoreService;
import pcy.study.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import pcy.study.api.domain.storemenu.controller.model.StoreMenuResponse;
import pcy.study.api.domain.storemenu.converter.StoreMenuConverter;
import pcy.study.api.domain.storemenu.service.StoreMenuService;
import pcy.study.common.annotation.Business;

import java.util.List;

@Business
@RequiredArgsConstructor
public class StoreMenuBusiness {

    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;
    private final StoreService storeService;

    public StoreMenuResponse register(StoreMenuRegisterRequest request) {
        var store = storeService.getStoreWithThrow(request.storeId());
        var entity = storeMenuConverter.toEntity(store, request);
        var newEntity = storeMenuService.register(entity);
        return storeMenuConverter.toResponse(newEntity);
    }

    public List<StoreMenuResponse> searchByStore(Long storeId) {
        var storeMenus = storeMenuService.searchByStoreId(storeId);
        return storeMenus.stream()
                .map(storeMenuConverter::toResponse)
                .toList();
    }
}
