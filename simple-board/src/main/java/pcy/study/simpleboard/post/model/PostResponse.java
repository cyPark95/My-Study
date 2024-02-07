package pcy.study.simpleboard.post.model;

import pcy.study.simpleboard.post.db.Post;
import pcy.study.simpleboard.reply.model.ReplyResponse;

import java.util.List;

public record PostResponse(
        Long id,
        Long boardId,
        String userName,
        String email,
        String title,
        String contents,
        List<ReplyResponse> replies
) {

    public static PostResponse of(Post post) {
        return new PostResponse(
                post.getId(),
                post.getBoardId(),
                post.getUserName(),
                post.getEmail(),
                post.getTitle(),
                post.getContents(),
                createReplies(post)
        );
    }

    private static List<ReplyResponse> createReplies(Post post) {
        return post.getReplies().stream()
                .map(ReplyResponse::of)
                .toList();
    }
}
