package pcy.study.simpleboard.post.model;

import org.springframework.data.domain.Page;
import pcy.study.simpleboard.common.model.Pagination;
import pcy.study.simpleboard.post.db.Post;

public record PostGetAllResponse(Pagination<PostGetResponse> posts) {

    public static PostGetAllResponse of(Page<Post> posts) {
        return new PostGetAllResponse(createPostGetResponses1(posts));
    }

    private static Pagination<PostGetResponse> createPostGetResponses1(Page<Post> posts) {
        return new Pagination<>(posts.map(PostGetResponse::of));
    }
}
