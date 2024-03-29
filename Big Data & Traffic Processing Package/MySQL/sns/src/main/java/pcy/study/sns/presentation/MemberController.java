package pcy.study.sns.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pcy.study.sns.application.usecase.RegisterMemberUsecase;
import pcy.study.sns.domain.member.dto.MemberDetails;
import pcy.study.sns.domain.member.dto.MemberDto;
import pcy.study.sns.domain.member.dto.MemberNicknameHistoryDto;
import pcy.study.sns.domain.member.dto.MemberRegisterCommand;
import pcy.study.sns.domain.member.service.MemberReadService;
import pcy.study.sns.domain.member.service.MemberWriteService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberReadService memberReadService;
    private final MemberWriteService memberWriteService;

    private final RegisterMemberUsecase registerMemberUsecase;

    @PostMapping
    public MemberDto register(@RequestBody MemberRegisterCommand command) {
        return registerMemberUsecase.execute(command);
    }

    @GetMapping("/{id}")
    public MemberDto getMember(@PathVariable("id") Long id) {
        return memberReadService.getMember(id);
    }

    @PutMapping("/nickname")
    public MemberDto changeNickname(
            @RequestBody String nickname,
            @AuthenticationPrincipal MemberDetails memberDetails
    ) {
        var id = memberDetails.id();
        memberWriteService.changeNickname(id, nickname);
        return memberReadService.getMember(id);
    }

    @GetMapping("/nickname-history")
    public List<MemberNicknameHistoryDto> getNicknameHistories(@AuthenticationPrincipal MemberDetails memberDetails) {
        var id = memberDetails.id();
        return memberReadService.getNicknameHistories(id);
    }
}
