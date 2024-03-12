package pcy.study.sns.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pcy.study.sns.domain.member.dto.MemberDto;
import pcy.study.sns.domain.member.dto.MemberRegisterCommand;
import pcy.study.sns.domain.member.entity.Member;
import pcy.study.sns.domain.member.service.MemberReadService;
import pcy.study.sns.domain.member.service.MemberWriteService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberReadService memberReadService;
    private final MemberWriteService memberWriteService;

    @PostMapping
    public void register(@RequestBody MemberRegisterCommand command) {
        var member = memberWriteService.register(command);
    }

    @GetMapping("/{id}")
    public MemberDto getMember(@PathVariable("id") Long id) {
        return memberReadService.getMember(id);
    }
}
