package pcy.study.api.domain.store.converter;

import pcy.study.api.common.annotation.Converter;
import pcy.study.api.common.api.error.ErrorCode;
import pcy.study.api.common.exception.ApiException;
import pcy.study.api.domain.store.controller.model.StoreRegisterRequest;
import pcy.study.api.domain.store.controller.model.StoreResponse;
import pcy.study.db.store.Store;

import java.util.Optional;

@Converter
public class StoreConverter {

    public Store toEntity(StoreRegisterRequest request) {
        return Optional.ofNullable(request)
                .map(it -> Store.builder()
                        .name(request.name())
                        .address(request.address())
                        .category(request.category())
                        .thumbnailUrl(request.thumbnailUrl())
                        .minimumAmount(request.minimumAmount())
                        .minimumDeliveryAmount(request.minimumDeliveryAmount())
                        .phoneNumber(request.phoneNumber())
                        .build())
                .orElseThrow(() -> new ApiException("StoreRegisterRequest is Null", ErrorCode.NULL_POINT));
    }

    public StoreResponse toResponse(Store store) {
        return Optional.ofNullable(store)
                .map(it -> StoreResponse.builder()
                        .id(store.getId())
                        .name(store.getName())
                        .address(store.getAddress())
                        .category(store.getCategory())
                        .star(store.getStar())
                        .thumbnailUrl(store.getThumbnailUrl())
                        .minimumAmount(store.getMinimumAmount())
                        .minimumDeliveryAmount(store.getMinimumDeliveryAmount())
                        .phoneNumber(store.getPhoneNumber())
                        .build())
                .orElseThrow(() -> new ApiException("Store Entity is Null", ErrorCode.NULL_POINT));
    }
}
