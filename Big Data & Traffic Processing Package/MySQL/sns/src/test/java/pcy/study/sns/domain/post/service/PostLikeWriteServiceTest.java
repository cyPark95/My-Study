package pcy.study.sns.domain.post.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pcy.study.sns.common.IntegrationTest;
import pcy.study.sns.util.MemberFixtureFactory;
import pcy.study.sns.util.PostFixtureFactory;

@IntegrationTest
class PostLikeWriteServiceTest {

    @Autowired
    private PostLikeWriteService postLikeWriteService;

    @Test
    @DisplayName("게시글 좋아요 등록")
    void register() {
        // given
        var member = MemberFixtureFactory.createMemberDto();
        var post = PostFixtureFactory.createPostDto(member.id());

        // when
        var result = postLikeWriteService.register(post, member);

        // then
        Assertions.assertNotNull(result);
    }
}
