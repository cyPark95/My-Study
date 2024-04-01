package pcy.study.sns.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pcy.study.sns.application.usecase.GetTimelinePostUsecase;
import pcy.study.sns.application.usecase.RegisterPostLikeUsecase;
import pcy.study.sns.application.usecase.RegisterPostUsecase;
import pcy.study.sns.domain.member.dto.MemberDetails;
import pcy.study.sns.domain.post.dto.DailyPostCount;
import pcy.study.sns.domain.post.dto.DailyPostCountRequest;
import pcy.study.sns.domain.post.dto.PostCommand;
import pcy.study.sns.domain.post.dto.PostDto;
import pcy.study.sns.domain.post.service.PostReadService;
import pcy.study.sns.domain.post.service.PostWriteService;
import pcy.study.sns.util.CursorRequest;
import pcy.study.sns.util.PageCursor;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("posts")
public class PostController {

    private final PostReadService postReadService;
    private final PostWriteService postWriteService;

    private final RegisterPostUsecase registerPostUsecase;
    private final GetTimelinePostUsecase getTimelinePostUsecase;
    private final RegisterPostLikeUsecase registerPostLikeUsecase;

    @PostMapping
    public Long register(
            @RequestBody PostCommand command,
            @AuthenticationPrincipal MemberDetails memberDetails
    ) {
        var memberId = memberDetails.id();
        return registerPostUsecase.execute(memberId, command);
    }

    @GetMapping("/daily-counts")
    public List<DailyPostCount> getDailyPostCounts(
            @ModelAttribute DailyPostCountRequest request,
            @AuthenticationPrincipal MemberDetails memberDetails
    ) {
        var memberId = memberDetails.id();
        return postReadService.getDailyPostCounts(memberId, request);
    }

    @GetMapping("/members")
    public Page<PostDto> getPosts(
            @RequestParam("size") int size,
            @RequestParam("page") int page,
            @AuthenticationPrincipal MemberDetails memberDetails
    ) {
        var pageRequest = PageRequest.of(page, size);
        var memberId = memberDetails.id();
        return postReadService.getPosts(memberId, pageRequest);
    }

    @GetMapping("/members/by-cursor")
    public PageCursor<PostDto> getPostsByCursor(
            @ModelAttribute CursorRequest cursorRequest,
            @AuthenticationPrincipal MemberDetails memberDetails
    ) {
        var memberId = memberDetails.id();
        return postReadService.getPosts(memberId, cursorRequest);
    }

    @GetMapping("/member/timeline")
    public PageCursor<PostDto> getTimeline(
            @ModelAttribute CursorRequest cursorRequest,
            @AuthenticationPrincipal MemberDetails memberDetails
    ) {
        var memberId = memberDetails.id();
        return getTimelinePostUsecase.execute(memberId, cursorRequest);
    }

    @PostMapping("/{postId}/v1/like")
    public void likePost(@PathVariable("postId") Long postId) {
        postWriteService.likePostByOptimisticLock(postId);
    }

    @PostMapping("/{postId}/v2/like")
    public void likePost(
            @PathVariable("postId") Long postId,
            @AuthenticationPrincipal MemberDetails memberDetails
    ) {
        var memberId = memberDetails.id();
        registerPostLikeUsecase.execute(postId, memberId);
    }
}
