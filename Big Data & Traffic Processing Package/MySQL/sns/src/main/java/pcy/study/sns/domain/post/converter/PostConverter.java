package pcy.study.sns.domain.post.converter;

import org.springframework.stereotype.Component;
import pcy.study.sns.domain.post.dto.PostDto;
import pcy.study.sns.domain.post.entity.Post;

import java.util.List;

@Component
public class PostConverter {

    public List<PostDto> toDto(List<Post> posts) {
        return posts.stream()
                .map(this::toDto)
                .toList();
    }

    public PostDto toDto(Post post) {
        return new PostDto(
                post.getId(),
                post.getMemberId(),
                post.getContents(),
                post.getCreatedDate(),
                post.getLikeCount()
        );
    }
}
