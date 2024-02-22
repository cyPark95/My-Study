package pcy.study.api.domain.user.controller.model;

import lombok.Builder;

@Builder
public record UserResponse(
        Long id,
        String name,
        String email,
        String address
) {
}
