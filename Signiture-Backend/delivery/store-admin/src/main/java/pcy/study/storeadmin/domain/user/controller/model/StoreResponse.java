package pcy.study.storeadmin.domain.user.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record StoreResponse(
        @Schema(description = "가게 식별값", example = "1")
        Long id,
        @Schema(description = "가게 이름", example = "이름")
        String name
) {
}
