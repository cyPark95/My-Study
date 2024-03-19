package pcy.study.sns.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pcy.study.sns.domain.follow.dto.FollowDto;
import pcy.study.sns.domain.follow.service.FollowReadService;
import pcy.study.sns.domain.post.dto.PostDto;
import pcy.study.sns.domain.post.service.PostReadService;
import pcy.study.sns.util.CursorRequest;
import pcy.study.sns.util.PageCursor;

@Service
@RequiredArgsConstructor
public class GetTimelinePostUsecase {

    private final FollowReadService followReadService;
    private final PostReadService postReadService;

    public PageCursor<PostDto> execute(Long memberId, CursorRequest cursorRequest) {
        // TODO SSE 연동
        /*
        1. memberId -> follow 조회
        2. 1번 결과로 게시물 조회
         */
        var followings = followReadService.getFollowings(memberId);
        var followingMemberIds = followings.stream()
                .map(FollowDto::toMemberId)
                .toList();
        return postReadService.getPosts(followingMemberIds, cursorRequest);
    }
}
