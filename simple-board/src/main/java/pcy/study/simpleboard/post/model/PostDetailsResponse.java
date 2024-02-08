package pcy.study.simpleboard.post.model;

import pcy.study.simpleboard.post.db.Post;
import pcy.study.simpleboard.reply.model.ReplyResponse;

import java.util.List;

public record PostDetailsResponse(
        Long id,
        Long boardId,
        String userName,
        String email,
        String title,
        String contents,
        List<ReplyResponse> replies
) {

    public static PostDetailsResponse of(Post post) {
        return new PostDetailsResponse(
                post.getId(),
                post.getBoard().getId(),
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
