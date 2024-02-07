package pcy.study.simpleboard.post.model;

import pcy.study.simpleboard.post.db.Post;

public record PostResponse(
        Long id,
        Long boardId,
        String userName,
        String email,
        String title,
        String contents
) {

    public static PostResponse of(Post post) {
        return new PostResponse(
                post.getId(),
                post.getBoardId(),
                post.getUserName(),
                post.getEmail(),
                post.getTitle(),
                post.getContents()
        );
    }
}
