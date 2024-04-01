package pcy.study.sns.domain.post.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pcy.study.sns.common.IntegrationTest;
import pcy.study.sns.domain.post.entity.Post;
import pcy.study.sns.domain.post.repository.PostLikeRepository;
import pcy.study.sns.domain.post.repository.PostRepository;
import pcy.study.sns.util.PostFixtureFactory;

import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@IntegrationTest
class PostLikeSchedulerTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostLikeRepository postLikeRepository;

    @Test
    @DisplayName("좋아요 수 동기화")
    void synchronousPostLikeCount() throws InterruptedException {
        // given
        var post = savePost();

        int size = 3;
        LongStream.range(0, size)
                .forEach(l -> {
                    var postLike = PostFixtureFactory.getPostLike(post.getId(), l);
                    postLikeRepository.save(postLike);
                });

        Thread.sleep(1_000L);

        // when
        var result = postRepository.findById(post.getId()).orElseThrow();

        // then
        assertEquals(size, result.getLikeCount());
    }

    private Post savePost() {
        var post = PostFixtureFactory.createPost(-1L);
        return postRepository.save(post);
    }
}
