package pcy.study.simpleboard.post.model;

import pcy.study.simpleboard.post.db.Post;

import java.util.List;

public record PostGetAllResponse(List<PostResponse> posts) {

    public static PostGetAllResponse of(List<Post> posts) {
        return new PostGetAllResponse(createPostResponses(posts));
    }

    private static List<PostResponse> createPostResponses(List<Post> posts) {
        return posts.stream()
                .map(PostResponse::of)
                .toList();
    }
}
