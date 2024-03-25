package pcy.study.sns.util;

import pcy.study.sns.domain.post.dto.PostDto;
import pcy.study.sns.domain.post.entity.Post;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostAssertUtil {

    public static void assertEqualsPost(Post post, PostDto result) {
        assertAll(
                () -> assertEquals(post.getMemberId(), result.memberId()),
                () -> assertEquals(post.getContents(), result.contents()),
                () -> assertEquals(post.getCreatedDate(), result.createdDate())
        );
    }
}
