package pcy.study.simpleboard.post.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostDetailsRequest(
        @NotNull
        Long id,
        @NotBlank
        String password
) {
}
