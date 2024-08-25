package pcy.study.api.domain.store.business;

import lombok.RequiredArgsConstructor;
import pcy.study.api.common.annotation.Business;
import pcy.study.api.domain.store.controller.model.StoreRegisterRequest;
import pcy.study.api.domain.store.controller.model.StoreResponse;
import pcy.study.api.domain.store.converter.StoreConverter;
import pcy.study.api.domain.store.service.StoreService;
import pcy.study.db.store.enums.StoreCategory;

import java.util.List;

@Business
@RequiredArgsConstructor
public class StoreBusiness {

    private final StoreService storeService;
    private final StoreConverter storeConverter;

    public StoreResponse register(StoreRegisterRequest request) {
        var entity = storeConverter.toEntity(request);
        var newEntity = storeService.register(entity);
        return storeConverter.toResponse(newEntity);
    }

    public List<StoreResponse> searchByCategory(StoreCategory category) {
        var stores = storeService.searchByCategory(category);
        return stores.stream()
                .map(storeConverter::toResponse)
                .toList();
    }
}
