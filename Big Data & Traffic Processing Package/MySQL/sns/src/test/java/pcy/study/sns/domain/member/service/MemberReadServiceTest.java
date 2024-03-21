package pcy.study.sns.domain.member.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pcy.study.sns.common.IntegrationTest;
import pcy.study.sns.domain.member.entity.Member;
import pcy.study.sns.domain.member.repository.MemberNicknameHistoryRepository;
import pcy.study.sns.domain.member.repository.MemberRepository;
import pcy.study.sns.util.MemberAssertUtil;
import pcy.study.sns.util.MemberFixtureFactory;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@IntegrationTest
class MemberReadServiceTest {

    @Autowired
    private MemberReadService memberReadService;

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    @Test
    @DisplayName("ID로 회원 조회")
    void getMember() {
        // given
        var member = saveMember();

        // when
        var result = memberReadService.getMember(member.getId());

        // then
        MemberAssertUtil.assertEqualsMember(member, result);
    }

    @Test
    @DisplayName("존재하지 않는 ID로 회원 조회")
    void getMember_NotFound() {
        // when
        // then
        assertThrows(
                NoSuchElementException.class,
                () -> memberReadService.getMember(-1L)
        );
    }

    @Test
    @DisplayName("ID 목록으로 회원 목록 조회")
    void getMembers() {
        // given
        var size = 10;
        var members = MemberFixtureFactory.createMembers(size);
        var ids = members.stream()
                .map(member -> memberRepository.save(member).getId())
                .toList();

        // when
        var results = memberReadService.getMembers(ids);

        // then
        assertEquals(size, results.size());

        for (int i = 0; i < size; i++) {
            MemberAssertUtil.assertEqualsMember(members.get(i), results.get(i));
        }
    }

    @Test
    @DisplayName("빈 ID 목록으로 회원 목록 조회")
    void getMembers_EmptyIds() {
        // when
        var results = memberReadService.getMembers(List.of());

        // then
        assertTrue(results.isEmpty());
    }

    @Test
    @DisplayName("닉네임 변경 내역 조회")
    void getNicknameHistories() {
        // given
        var memberId = 1L;
        saveMember();

        var size = 10;
        var histories = MemberFixtureFactory.createMemberNicknameHistories(size, memberId);
        memberNicknameHistoryRepository.saveAll(histories);

        // when
        var results = memberReadService.getNicknameHistories(memberId);

        // then
        assertEquals(size, results.size());

        for (int i = 0; i < size; i++) {
            MemberAssertUtil.assertEqualsMemberNicknameHistory(histories.get(i), results.get(i));
        }
    }

    private Member saveMember() {
        Member member = MemberFixtureFactory.createMember();
        return memberRepository.save(member);
    }

}
