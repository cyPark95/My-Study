package pcy.study.simpleboard.post.model;

import pcy.study.simpleboard.post.db.Post;

public record PostGetResponse(
        Long id,
        Long boardId,
        String userName,
        String email,
        String title,
        String contents
) {

    public static PostGetResponse of(Post post) {
        return new PostGetResponse(
                post.getId(),
                post.getBoard().getId(),
                post.getUserName(),
                post.getEmail(),
                post.getTitle(),
                post.getContents()
        );
    }
}
