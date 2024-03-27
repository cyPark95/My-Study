package pcy.study.sns.application.usecase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pcy.study.sns.common.IntegrationTest;
import pcy.study.sns.domain.follow.repository.FollowRepository;
import pcy.study.sns.domain.member.entity.Member;
import pcy.study.sns.domain.member.repository.MemberRepository;
import pcy.study.sns.util.FollowFixtureFactory;
import pcy.study.sns.util.MemberAssertUtil;
import pcy.study.sns.util.MemberFixtureFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@IntegrationTest
@DisplayName("팔로워 회원 조회")
class GetFollowingMemberUsecaseTest {

    @Autowired
    private GetFollowingMemberUsecase getFollowingMemberUsecase;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private FollowRepository followRepository;

    @Test
    void execute() {
        // given
        int size = 3;
        var fromMember = saveFromMember();
        var toMembers = saveToMembers(size);
        following(toMembers, fromMember);

        // when
        var result = getFollowingMemberUsecase.execute(fromMember.getId());

        // then
        assertEquals(size, result.size());

        for (int i = 0; i < size; i++) {
            MemberAssertUtil.assertEqualsMember(toMembers.get(i), result.get(i));
        }
    }

    private Member saveFromMember() {
        var member = MemberFixtureFactory.createMember();
        return memberRepository.save(member);
    }

    private List<Member> saveToMembers(int size) {
        return MemberFixtureFactory.createMembers(size).stream()
                .map(memberRepository::save)
                .toList();
    }

    private void following(List<Member> toMembers, Member fromMember) {
        var follows = toMembers.stream()
                .map(toMember -> FollowFixtureFactory.createFollow(fromMember.getId(), toMember.getId()))
                .toList();
        followRepository.saveAll(follows);
    }
}
