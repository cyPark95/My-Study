package pcy.study.sns.domain.member.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pcy.study.sns.common.IntegrationTest;
import pcy.study.sns.domain.member.dto.MemberRegisterCommand;
import pcy.study.sns.domain.member.entity.Member;
import pcy.study.sns.domain.member.entity.MemberNicknameHistory;
import pcy.study.sns.domain.member.repository.MemberNicknameHistoryRepository;
import pcy.study.sns.domain.member.repository.MemberRepository;
import pcy.study.sns.util.MemberFixtureFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@IntegrationTest
class MemberWriteServiceTest {

    @Autowired
    private MemberWriteService memberWriteService;

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    @Test
    @DisplayName("회원 등록")
    void register() {
        // given
        var command = new MemberRegisterCommand(
                "email",
                "password",
                "nickname",
                LocalDate.now()
        );
        String encodedPassword = "encodedPassword";

        // when
        var result = memberWriteService.register(command, encodedPassword);

        // then
        Assertions.assertNotNull(result.id());

        Assertions.assertEquals(command.email(), result.email());
        Assertions.assertEquals(command.nickname(), result.nickname());
        Assertions.assertEquals(command.birthday(), result.birthday());
    }

    @Test
    @DisplayName("회원 닉네임 변경")
    void changeNickname() {
        // given
        var member = saveMember();
        var changeNickname = "change";

        // when
        memberWriteService.changeNickname(member.getId(), changeNickname);

        // then
        var result = memberRepository.findById(member.getId()).orElseThrow();
        assertEquals(changeNickname, result.getNickname());
    }

    @Test
    @DisplayName("회원 닉네임 변경 시, 변경 닉네임 히스토리 저장")
    void changeNicknameHistory() {
        // given
        var member = saveMember();
        var changeNickname = "change";
        memberWriteService.changeNickname(member.getId(), changeNickname);

        // when
        var results = memberNicknameHistoryRepository.findAllByMemberId(member.getId());

        // then
        assertEquals(1, results.size());

        MemberNicknameHistory memberNicknameHistory = results.get(0);
        assertEquals(member.getId(), memberNicknameHistory.getMemberId());
        assertEquals(changeNickname, memberNicknameHistory.getNickname());
    }

    private Member saveMember() {
        Member member = MemberFixtureFactory.createMember();
        return memberRepository.save(member);
    }
}
