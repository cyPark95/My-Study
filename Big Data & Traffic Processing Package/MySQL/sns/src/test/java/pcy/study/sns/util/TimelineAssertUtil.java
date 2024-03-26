package pcy.study.sns.util;

import pcy.study.sns.domain.post.dto.TimelineDto;
import pcy.study.sns.domain.post.entity.Timeline;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimelineAssertUtil {

    public static void assertEqualsTimeline(Timeline timeline, TimelineDto result) {
        assertAll(
                () -> assertEquals(timeline.getId(), result.id()),
                () -> assertEquals(timeline.getMemberId(), result.memberId()),
                () -> assertEquals(timeline.getPostId(), result.postId())
        );
    }
}
