package pcy.study.sns.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pcy.study.sns.domain.member.dto.MemberDto;
import pcy.study.sns.domain.member.dto.MemberRegisterCommand;
import pcy.study.sns.domain.member.service.MemberWriteService;

@Service
@RequiredArgsConstructor
public class RegisterMemberUsecase {

    private final MemberWriteService memberWriteService;
    private final PasswordEncoder passwordEncoder;

    public MemberDto execute(MemberRegisterCommand command) {
        var encodedPassword = passwordEncoder.encode(command.password());
        return memberWriteService.register(command, encodedPassword);
    }
}
