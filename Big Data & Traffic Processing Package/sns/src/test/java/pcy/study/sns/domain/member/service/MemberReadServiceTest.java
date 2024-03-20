package pcy.study.sns.domain.member.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import pcy.study.sns.domain.member.converter.MemberConverter;
import pcy.study.sns.domain.member.dto.MemberDto;
import pcy.study.sns.domain.member.dto.MemberNicknameHistoryDto;
import pcy.study.sns.domain.member.entity.Member;
import pcy.study.sns.domain.member.entity.MemberNicknameHistory;
import pcy.study.sns.domain.member.repository.MemberNicknameHistoryRepository;
import pcy.study.sns.domain.member.repository.MemberRepository;
import pcy.study.sns.util.MemberAssertUtil;
import pcy.study.sns.util.MemberFixtureFactory;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberReadServiceTest {

    @InjectMocks
    private MemberReadService memberReadService;

    @Mock
    private MemberRepository memberRepository;
    @Spy
    private MemberConverter memberConverter;
    @Mock
    private MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    @Test
    @DisplayName("ID로 회원 조회")
    void getMember() {
        // given
        Member member = MemberFixtureFactory.create();
        when(memberRepository.findById(member.getId())).thenReturn(Optional.of(member));

        // when
        MemberDto result = memberReadService.getMember(member.getId());

        // then
    }

    @Test
    @DisplayName("ID 목록으로 회원 목록 조회")
    void getMembers() {
        // given
        int size = 10;
        List<Member> members = MemberFixtureFactory.createMembers(size);
        List<Long> ids = extractMemberIds(members);
        when(memberRepository.findAllByIdIn(ids)).thenReturn(members);

        // when
        List<MemberDto> result = memberReadService.getMembers(ids);

        // then
        assertEquals(size, result.size());

        for (int i = 0; i < size; i++) {
            MemberAssertUtil.assertEqualsMember(members.get(i), result.get(i));
        }
    }

    @Test
    @DisplayName("닉네임 변경 내역 조회")
    void getNicknameHistories() {
        // given
        Long memberId = 1L;
        int size = 10;
        List<MemberNicknameHistory> histories = MemberFixtureFactory.createMemberNicknameHistories(size, memberId);
        when(memberNicknameHistoryRepository.findAllByMemberId(memberId)).thenReturn(histories);

        // when
        List<MemberNicknameHistoryDto> result = memberReadService.getNicknameHistories(memberId);

        // then
        assertEquals(size, result.size());

        for (int i = 0; i < size; i++) {
            MemberAssertUtil.assertEqualsMemberNicknameHistory(histories.get(i), result.get(i));
        }
    }

    private static List<Long> extractMemberIds(List<Member> members) {
        return members.stream()
                .map(Member::getId)
                .toList();
    }
}
