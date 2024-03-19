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
import pcy.study.sns.domain.member.dto.MemberRegisterCommand;
import pcy.study.sns.domain.member.entity.Member;
import pcy.study.sns.domain.member.entity.MemberNicknameHistory;
import pcy.study.sns.domain.member.repository.MemberNicknameHistoryRepository;
import pcy.study.sns.domain.member.repository.MemberRepository;
import pcy.study.sns.util.MemberFixtureFactory;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberWriteServiceTest {

    @InjectMocks
    private MemberWriteService memberWriteService;

    @Mock
    private MemberRepository memberRepository;
    @Spy
    private MemberConverter memberConverter;
    @Mock
    private MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    @Test
    @DisplayName("회원 등록")
    void register() {
        // given
        MemberRegisterCommand command = new MemberRegisterCommand("email", "nickname", LocalDate.now());

        Member member = MemberFixtureFactory.createWithId();
        when(memberRepository.save(any(Member.class))).thenReturn(member);

        // when
        MemberDto result = memberWriteService.register(command);

        // then
        assertAll(
                () -> assertEquals(member.getId(), result.id()),
                () -> assertEquals(member.getEmail(), result.email()),
                () -> assertEquals(member.getNickname(), result.nickname()),
                () -> assertEquals(member.getBirthday(), result.birthday())
        );

        verify(memberNicknameHistoryRepository, atMostOnce()).save(any(MemberNicknameHistory.class));
    }

    @Test
    @DisplayName("닉네임 변경")
    void changeNickname() {
        // given
        String changeNickname = "change";

        Member member = MemberFixtureFactory.createWithId();
        when(memberRepository.findById(member.getId())).thenReturn(Optional.of(member));

        // when
        memberWriteService.changeNickname(member.getId(), changeNickname);

        // then
        assertEquals(changeNickname, member.getNickname());

        verify(memberRepository, atMostOnce()).save(member);
        verify(memberNicknameHistoryRepository, atMostOnce()).save(any(MemberNicknameHistory.class));
    }
}
