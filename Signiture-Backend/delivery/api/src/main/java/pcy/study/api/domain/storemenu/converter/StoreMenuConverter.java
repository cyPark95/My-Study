package pcy.study.api.domain.storemenu.converter;

import pcy.study.api.common.annotation.Converter;
import pcy.study.api.common.api.error.ErrorCode;
import pcy.study.api.common.exception.ApiException;
import pcy.study.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import pcy.study.api.domain.storemenu.controller.model.StoreMenuResponse;
import pcy.study.db.storemenu.StoreMenu;

import java.util.List;
import java.util.Optional;

@Converter
public class StoreMenuConverter {

    public StoreMenu toEntity(StoreMenuRegisterRequest request) {
        return Optional.ofNullable(request)
                .map(it -> StoreMenu.builder()
                        .storeId(request.storeId())
                        .name(request.name())
                        .amount(request.amount())
                        .thumbnailUrl(request.thumbnailUrl())
                        .build())
                .orElseThrow(() -> new ApiException("StoreMenuRequest is Null", ErrorCode.NULL_POINT));
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
                        .storeId(storeMenu.getStoreId())
                        .name(storeMenu.getName())
                        .amount(storeMenu.getAmount())
                        .thumbnailUrl(storeMenu.getThumbnailUrl())
                        .likeCount(storeMenu.getLikeCount())
                        .sequence(storeMenu.getSequence())
                        .build())
                .orElseThrow(() -> new ApiException("StoreMenu Entity is Null", ErrorCode.NULL_POINT));
    }
}
