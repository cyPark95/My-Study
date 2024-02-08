package pcy.study.simpleboard.post.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import pcy.study.simpleboard.post.db.Post;

public record PostCreateRequest(
        @NotNull
        Long boardId,
        @NotBlank
        String userName,
        @NotBlank
        @Size(min = 4, max = 4)
        String password,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String title,
        @NotBlank
        String contents
) {

    public Post toPost() {
        return Post.builder()
                .userName(userName)
                .password(password)
                .email(email)
                .title(title)
                .contents(contents)
                .build();
    }
}
