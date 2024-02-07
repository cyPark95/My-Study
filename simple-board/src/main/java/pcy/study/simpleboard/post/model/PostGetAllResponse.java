package pcy.study.simpleboard.post.model;

import pcy.study.simpleboard.post.db.Post;

import java.util.List;

public record PostGetAllResponse(List<PostGetResponse> posts) {

    public static PostGetAllResponse of(List<Post> posts) {
        return new PostGetAllResponse(createPostGetResponses(posts));
    }

    private static List<PostGetResponse> createPostGetResponses(List<Post> posts) {
        return posts.stream()
                .map(PostGetResponse::of)
                .toList();
    }
}
