package pcy.study.sns.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pcy.study.sns.domain.follow.dto.FollowDto;
import pcy.study.sns.domain.follow.service.FollowReadService;
import pcy.study.sns.domain.post.dto.PostCommand;
import pcy.study.sns.domain.post.service.PostWriteService;
import pcy.study.sns.domain.post.service.TimelineWriteService;

@Service
@RequiredArgsConstructor
public class RegisterPostUsecase {

    private final PostWriteService postWriteService;
    private final FollowReadService followReadService;
    private final TimelineWriteService timelineWriteService;

    public Long execute(Long memberId, PostCommand command) {
        var postId = postWriteService.register(memberId, command);
        var followMemberIds = followReadService.getFollowers(memberId).stream()
                .map(FollowDto::fromMemberId)
                .toList();
        // TODO SSE 연동
        timelineWriteService.deliveryToTimeline(postId, followMemberIds);
        return postId;
    }
}
