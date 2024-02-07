package pcy.study.simpleboard.post.model;

import jakarta.validation.constraints.NotBlank;

public record PostGetRequest(
        @NotBlank
        String password
) {
}
