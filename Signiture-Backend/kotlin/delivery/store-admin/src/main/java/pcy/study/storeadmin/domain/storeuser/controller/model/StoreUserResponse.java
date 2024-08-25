package pcy.study.storeadmin.domain.storeuser.controller.model;

import lombok.Builder;

@Builder
public record StoreUserResponse(
        UserResponse user,
        StoreResponse store
) {
}
