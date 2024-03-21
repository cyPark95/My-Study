package pcy.study.sns.util;

import pcy.study.sns.domain.follow.dto.FollowDto;
import pcy.study.sns.domain.follow.entity.Follow;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FollowAssertUtil {

    public static void assertEqualsFollow(Follow follow, FollowDto result) {
        assertAll(
                () -> assertEquals(follow.getId(), result.id()),
                () -> assertEquals(follow.getToMemberId(), result.toMemberId()),
                () -> assertEquals(follow.getFromMemberId(), result.fromMemberId())
        );
    }
}
