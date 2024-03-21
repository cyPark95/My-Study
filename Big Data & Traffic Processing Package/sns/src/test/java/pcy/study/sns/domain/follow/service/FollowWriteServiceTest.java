package pcy.study.sns.domain.follow.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import pcy.study.sns.common.IntegrationTest;
import pcy.study.sns.domain.follow.repository.FollowRepository;
import pcy.study.sns.domain.member.dto.MemberDto;
import pcy.study.sns.util.FollowFixtureFactory;
import pcy.study.sns.util.MemberFixtureFactory;

import static org.junit.jupiter.api.Assertions.*;

@IntegrationTest
class FollowWriteServiceTest {

    @Autowired
    private FollowWriteService followWriteService;

    @Autowired
    private FollowRepository followRepository;

    private MemberDto fromMember;

    @BeforeEach
    void setUp() {
        // given
        fromMember = MemberFixtureFactory.createMemberDto();
    }

    @Test
    @DisplayName("팔로우 등록")
    void register() {
        // given
        var toMember = MemberFixtureFactory.createMemberDto();

        // when
        var result = followWriteService.register(fromMember, toMember);

        // then
        assertNotNull(result.id());
        assertEquals(fromMember.id(), result.fromMemberId());
        assertEquals(toMember.id(), result.toMemberId());
    }

    @Test
    @DisplayName("본인 계정 팔로우")
    void register_Self() {
        // when
        // then
        assertThrows(
                IllegalArgumentException.class,
                () -> followWriteService.register(fromMember, fromMember)
        );
    }

    @Test
    @DisplayName("이미 등록된 팔로우 등록")
    void register_Duplicated() {
        // given
        var toMember = MemberFixtureFactory.createMemberDto();
        var follow = FollowFixtureFactory.createFollow(fromMember.id(), toMember.id());
        followRepository.save(follow);

        // when
        // then
        assertThrows(
                DataIntegrityViolationException.class,
                () -> followWriteService.register(fromMember, toMember)
        );
    }
}
