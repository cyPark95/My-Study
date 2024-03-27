package pcy.study.sns.util;

import pcy.study.sns.domain.post.dto.PostDto;
import pcy.study.sns.domain.post.dto.TimelineDto;
import pcy.study.sns.domain.post.entity.Post;
import pcy.study.sns.domain.post.entity.Timeline;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostAssertUtil {

    public static void assertEqualsPost(Post post, PostDto result) {
        assertAll(
                () -> assertEquals(post.getId(), result.id()),
                () -> assertEquals(post.getMemberId(), result.memberId()),
                () -> assertEquals(post.getContents(), result.contents()),
                () -> assertEquals(post.getCreatedDate(), result.createdDate())
        );
    }

    public static void assertEqualsTimeline(Timeline timeline, TimelineDto result) {
        assertAll(
                () -> assertEquals(timeline.getId(), result.id()),
                () -> assertEquals(timeline.getMemberId(), result.memberId()),
                () -> assertEquals(timeline.getPostId(), result.postId())
        );
    }
}
