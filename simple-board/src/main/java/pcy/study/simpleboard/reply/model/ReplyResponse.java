package pcy.study.simpleboard.reply.model;

import pcy.study.simpleboard.reply.db.Reply;

public record ReplyResponse(
        Long id,
        Long postId,
        String userName,
        String title,
        String contents
) {

    public static ReplyResponse of(Reply reply) {
        return new ReplyResponse(
                reply.getId(),
                reply.getPostId(),
                reply.getUserName(),
                reply.getTitle(),
                reply.getContents()
        );
    }
}
