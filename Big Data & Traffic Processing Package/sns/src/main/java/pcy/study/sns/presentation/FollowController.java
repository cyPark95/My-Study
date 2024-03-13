package pcy.study.sns.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pcy.study.sns.application.usecase.RegisterFollowMemberUsecase;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follow")
public class FollowController {

    private final RegisterFollowMemberUsecase registerFollowMemberUsecase;

    @PostMapping("/{fromId}/{toId}")
    public void register(
            @PathVariable("fromId") Long fromId,
            @PathVariable("toId") Long toId
    ) {
        registerFollowMemberUsecase.execute(fromId, toId);
    }
}
