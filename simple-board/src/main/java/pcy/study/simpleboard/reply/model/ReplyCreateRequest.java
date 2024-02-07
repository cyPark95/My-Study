package pcy.study.simpleboard.reply.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import pcy.study.simpleboard.reply.db.Reply;

public record ReplyCreateRequest(
        @NotNull
        Long postId,
        @NotBlank
        String userName,
        @NotBlank
        String password,
        @NotBlank
        String title,
        @NotBlank
        String contents
) {

    public Reply toReply() {
        return Reply.builder()
                .postId(postId)
                .userName(userName)
                .password(password)
                .title(title)
                .contents(contents)
                .build();
    }
}
