package pcy.study.simpleboard.reply.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReplyDeleteRequest(
        @NotNull
        Long id,
        @NotBlank
        String password
) {
}
