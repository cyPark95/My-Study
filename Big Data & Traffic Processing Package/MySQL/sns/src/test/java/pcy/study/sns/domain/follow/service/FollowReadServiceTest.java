package pcy.study.sns.domain.follow.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pcy.study.sns.common.IntegrationTest;
import pcy.study.sns.domain.follow.entity.Follow;
import pcy.study.sns.domain.follow.repository.FollowRepository;
import pcy.study.sns.util.FollowAssertUtil;
import pcy.study.sns.util.FollowFixtureFactory;

import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@IntegrationTest
class FollowReadServiceTest {

    @Autowired
    private FollowReadService followReadService;

    @Autowired
    private FollowRepository followRepository;

    @Test
    @DisplayName("팔로잉 조회")
    void getFollowings() {
        // given
        var size = 10;
        var fromMemberId = -1L;
        var follows = LongStream.range(0, size)
                .mapToObj(toMemberId -> saveFollow(fromMemberId, toMemberId))
                .toList();

        // when
        var results = followReadService.getFollowings(fromMemberId);

        // then
        assertEquals(size, results.size());

        for (int i = 0; i < size; i++) {
            FollowAssertUtil.assertEqualsFollow(follows.get(i), results.get(i));
        }
    }

    @Test
    @DisplayName("팔로워 조회")
    void getFollowers() {
        // given
        var size = 10;
        var toMemberId = -1L;
        var follows = LongStream.range(0, size)
                .mapToObj(fromMemberId -> saveFollow(fromMemberId, toMemberId))
                .toList();

        // when
        var results = followReadService.getFollowers(toMemberId);

        // then
        assertEquals(size, results.size());

        for (int i = 0; i < size; i++) {
            FollowAssertUtil.assertEqualsFollow(follows.get(i), results.get(i));
        }
    }

    private Follow saveFollow(Long fromMemberId, Long toMemberId) {
        Follow follow = FollowFixtureFactory.createFollow(fromMemberId, toMemberId);
        return followRepository.save(follow);
    }
}
