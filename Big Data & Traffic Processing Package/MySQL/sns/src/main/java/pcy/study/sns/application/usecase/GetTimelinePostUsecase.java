package pcy.study.sns.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pcy.study.sns.domain.follow.service.FollowReadService;
import pcy.study.sns.domain.post.dto.PostDto;
import pcy.study.sns.domain.post.dto.TimelineDto;
import pcy.study.sns.domain.post.service.PostReadService;
import pcy.study.sns.domain.post.service.TimelineReadService;
import pcy.study.sns.util.CursorRequest;
import pcy.study.sns.util.PageCursor;

@Service
@RequiredArgsConstructor
public class GetTimelinePostUsecase {

    private final FollowReadService followReadService;
    private final PostReadService postReadService;
    private final TimelineReadService timelineReadService;

    public PageCursor<PostDto> execute(Long memberId, CursorRequest cursorRequest) {
        /*
        1. Timeline 조회
        2. 1번에 해당하는 게시물을 조회
         */
        var pagedTimelines = timelineReadService.getTimelines(memberId, cursorRequest);
        var postIds = pagedTimelines.body().stream()
                .map(TimelineDto::postId)
                .toList();
        var posts = postReadService.getPosts(postIds);
        return new PageCursor<>(pagedTimelines.nextCursorRequest(), posts);
    }
}
