package pcy.study.api.domain.store.converter;

import pcy.study.common.annotation.Converter;
import pcy.study.common.api.code.ErrorCode;
import pcy.study.common.exception.ApiException;
import pcy.study.api.domain.store.controller.model.StoreRegisterRequest;
import pcy.study.api.domain.store.controller.model.StoreResponse;
import pcy.study.db.store.Store;

import java.util.Optional;

@Converter
public class StoreConverter {

    public Store toEntity(StoreRegisterRequest request) {
        return Optional.ofNullable(request)
                .map(it -> new Store(
                        request.name(),
                        request.address(),
                        request.category(),
                        request.thumbnailUrl(),
                        request.minimumAmount(),
                        request.minimumDeliveryAmount(),
                        request.phoneNumber()
                ))
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "StoreRegisterRequest is Null"));
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
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "Store Entity is Null"));
    }
}
