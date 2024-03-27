package pcy.study.sns.application.usecase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pcy.study.sns.common.IntegrationTest;
import pcy.study.sns.domain.post.repository.PostLikeRepository;
import pcy.study.sns.util.StepUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

@IntegrationTest
@DisplayName("게시글 좋아요를 등록한다.")
class RegisterPostLikeUsecaseTest {

    @Autowired
    private RegisterPostLikeUsecase registerPostLikeUsecase;

    @Autowired
    private StepUtil stepUtil;

    @Autowired
    private PostLikeRepository postLikeRepository;

    @Test
    void execute() {
        // given
        var member = stepUtil.saveMember();
        var post = stepUtil.savePost(member.getId());

        // when
        registerPostLikeUsecase.execute(post.getId(), member.getId());

        // then
        var postLikes = postLikeRepository.findAll();
        assertEquals(1, postLikes.size());

        var result = postLikes.get(0);
        assertEquals(member.getId(), result.getMemberId());
        assertEquals(post.getId(), result.getPostId());
    }

}
