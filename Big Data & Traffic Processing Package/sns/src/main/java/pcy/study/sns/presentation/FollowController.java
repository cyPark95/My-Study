package pcy.study.sns.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pcy.study.sns.application.usecase.GetFollowingMemberUsecase;
import pcy.study.sns.application.usecase.RegisterFollowMemberUsecase;
import pcy.study.sns.domain.member.dto.MemberDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follow")
public class FollowController {

    private final RegisterFollowMemberUsecase registerFollowMemberUsecase;
    private final GetFollowingMemberUsecase getFollowingMemberUsecase;

    @PostMapping("/{fromId}/{toId}")
    public void register(
            @PathVariable("fromId") Long fromId,
            @PathVariable("toId") Long toId
    ) {
        registerFollowMemberUsecase.execute(fromId, toId);
    }

    @GetMapping("/{fromId}")
    public List<MemberDto> getFollowingMember(@PathVariable("fromId") Long fromId) {
        return getFollowingMemberUsecase.execute(fromId);
    }
}
