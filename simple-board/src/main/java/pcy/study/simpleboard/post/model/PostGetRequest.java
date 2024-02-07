package pcy.study.simpleboard.post.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostGetRequest(
        @NotNull
        Long id,
        @NotBlank
        String password
) {
}
