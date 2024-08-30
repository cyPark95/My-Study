package pcy.study.api.domain.storemenu.converter;

import pcy.study.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import pcy.study.api.domain.storemenu.controller.model.StoreMenuResponse;
import pcy.study.common.annotation.Converter;
import pcy.study.common.api.code.ErrorCode;
import pcy.study.common.exception.ApiException;
import pcy.study.db.store.Store;
import pcy.study.db.storemenu.StoreMenu;

import java.util.List;
import java.util.Optional;

@Converter
public class StoreMenuConverter {

    public StoreMenu toEntity(Store store, StoreMenuRegisterRequest request) {
        return Optional.ofNullable(request)
                .map(it -> new StoreMenu(
                        store,
                        request.name(),
                        request.amount(),
                        request.thumbnailUrl()
                ))
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "StoreMenuRequest is Null"));
    }

    public List<StoreMenuResponse> toResponse(List<StoreMenu> storeMenus) {
        return storeMenus.stream()
                .map(this::toResponse)
                .toList();
    }

    public StoreMenuResponse toResponse(StoreMenu storeMenu) {
        return Optional.ofNullable(storeMenu)
                .map(it -> StoreMenuResponse.builder()
                        .id(storeMenu.getId())
                        .storeId(storeMenu.getStore().getId())
                        .name(storeMenu.getName())
                        .amount(storeMenu.getAmount())
                        .thumbnailUrl(storeMenu.getThumbnailUrl())
                        .likeCount(storeMenu.getLikeCount())
                        .sequence(storeMenu.getSequence())
                        .build())
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "StoreMenu Entity is Null"));
    }
}
