package pcy.study.storeadmin.domain.user.controller.model;

import lombok.Builder;

@Builder
public record StoreUserResponse(
        UserResponse user,
        StoreResponse store
) {
}
