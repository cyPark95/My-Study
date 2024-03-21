package pcy.study.sns.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pcy.study.sns.domain.member.service.MemberReadService;
import pcy.study.sns.domain.post.service.PostLikeWriteService;
import pcy.study.sns.domain.post.service.PostReadService;

@Service
@RequiredArgsConstructor
public class RegisterPostLikeUsecase {

    private final PostReadService postReadService;
    private final MemberReadService memberReadService;
    private final PostLikeWriteService postLikeWriteService;

    public void execute(Long postId, Long memberId) {
        var post = postReadService.getPost(postId);
        var member = memberReadService.getMember(memberId);
        postLikeWriteService.register(post, member);
    }
}
