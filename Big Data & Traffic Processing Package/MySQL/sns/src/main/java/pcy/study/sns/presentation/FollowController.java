package pcy.study.sns.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pcy.study.sns.application.usecase.GetFollowingMemberUsecase;
import pcy.study.sns.application.usecase.RegisterFollowMemberUsecase;
import pcy.study.sns.domain.member.dto.MemberDetails;
import pcy.study.sns.domain.member.dto.MemberDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follow")
public class FollowController {

    private final RegisterFollowMemberUsecase registerFollowMemberUsecase;
    private final GetFollowingMemberUsecase getFollowingMemberUsecase;

    @PostMapping("/{toId}")
    public void register(
            @PathVariable("toId") Long toId,
            @AuthenticationPrincipal MemberDetails memberDetails
    ) {
        var fromId = memberDetails.id();
        registerFollowMemberUsecase.execute(fromId, toId);
    }

    @GetMapping("/{fromId}")
    public List<MemberDto> getFollowingMember(@PathVariable("fromId") Long fromId) {
        return getFollowingMemberUsecase.execute(fromId);
    }
}
