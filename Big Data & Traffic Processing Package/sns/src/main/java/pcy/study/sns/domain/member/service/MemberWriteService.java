package pcy.study.sns.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pcy.study.sns.domain.member.dto.MemberRegisterCommand;
import pcy.study.sns.domain.member.entity.Member;
import pcy.study.sns.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberWriteService {

    private final MemberRepository memberRepository;

    public void create(MemberRegisterCommand command) {
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

        memberRepository.save(member);
    }
}
