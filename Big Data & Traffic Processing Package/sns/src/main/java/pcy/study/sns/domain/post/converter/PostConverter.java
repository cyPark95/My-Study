package pcy.study.sns.domain.post.converter;

import org.springframework.stereotype.Component;
import pcy.study.sns.domain.post.dto.PostDto;
import pcy.study.sns.domain.post.entity.Post;

@Component
public class PostConverter {

    public PostDto toDto(Post post) {
        return new PostDto(
                post.getId(),
                post.getMemberId(),
                post.getContents(),
                post.getCreatedDate()
        );
    }
}
