package pcy.study.sns.util;

import pcy.study.sns.domain.follow.entity.Follow;

public class FollowFixtureFactory {

    public static Follow createFollow(Long fromMemberId, Long toMemberId) {
        return Follow.builder()
                .fromMemberId(fromMemberId)
                .toMemberId(toMemberId)
                .build();
    }
}
