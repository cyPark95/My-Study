package pcy.study.api.domain.storemenu.business;

import lombok.RequiredArgsConstructor;
import pcy.study.common.annotation.Business;
import pcy.study.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import pcy.study.api.domain.storemenu.controller.model.StoreMenuResponse;
import pcy.study.api.domain.storemenu.converter.StoreMenuConverter;
import pcy.study.api.domain.storemenu.service.StoreMenuService;

import java.util.List;

@Business
@RequiredArgsConstructor
public class StoreMenuBusiness {

    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;

    public StoreMenuResponse register(StoreMenuRegisterRequest request) {
        var entity = storeMenuConverter.toEntity(request);
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
