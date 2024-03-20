package pcy.study.sns.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pcy.study.sns.domain.member.converter.MemberConverter;
import pcy.study.sns.domain.member.dto.MemberDto;
import pcy.study.sns.domain.member.dto.MemberRegisterCommand;
import pcy.study.sns.domain.member.entity.Member;
import pcy.study.sns.domain.member.entity.MemberNicknameHistory;
import pcy.study.sns.domain.member.repository.MemberNicknameHistoryRepository;
import pcy.study.sns.domain.member.repository.MemberRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberWriteService {

    private final MemberRepository memberRepository;
    private final MemberConverter memberConverter;
    private final MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    public MemberDto register(MemberRegisterCommand command) {
        /*
        목표 - 회원정보(이메일, 닉네임, 생년월일)를 등록한다.
            - 닉네임은 10자를 넘길 수 없다.
        파라미터 - memberRegisterCommand

        var member = Member.of(memberRegisterCommand)
        memberRepository.save()
         */
        var member = Member.builder()
                .email(command.email())
                .nickname(command.nickname())
                .birthday(command.birthday())
                .build();

        var newMember = memberRepository.save(member);
        saveMemberNicknameHistory(newMember);
        return memberConverter.toDto(newMember);
    }

    public void changeNickname(Long id, String nickname) {
        /*
        1. 회원의 이름을 변경
        2. 변경 내역을 저장한다.
         */
        var member = memberRepository.findById(id)
                .orElseThrow();
        member.changeNickname(nickname);
        memberRepository.save(member);
        saveMemberNicknameHistory(member);
    }

    private void saveMemberNicknameHistory(Member member) {
        var history = MemberNicknameHistory.builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .build();
        memberNicknameHistoryRepository.save(history);
    }
}
