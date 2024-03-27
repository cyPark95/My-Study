package pcy.study.sns.domain.post.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pcy.study.sns.common.IntegrationTest;
import pcy.study.sns.domain.post.dto.PostCommand;
import pcy.study.sns.domain.post.entity.Post;
import pcy.study.sns.domain.post.repository.PostRepository;
import pcy.study.sns.util.PostFixtureFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@IntegrationTest
class PostWriteServiceTest {

    @Autowired
    private PostWriteService postWriteService;

    @Autowired
    private PostRepository postRepository;

    @Test
    @DisplayName("게시글 등록")
    void register() {
        // given
        var command = new PostCommand(
                1L,
                "contents"
        );

        // when
        var result = postWriteService.register(command);

        // then
        Assertions.assertNotNull(result);
    }

    @Test
    @DisplayName("게시물 좋아요 증가")
    void likePost() {
        // given
        var post = savePost();

        // when
        postWriteService.likePostByOptimisticLock(post.getId());

        // then
        var result = postRepository.findById(post.getId()).orElseThrow();
        assertEquals(1, result.getLikeCount());
    }

    @Test
    @DisplayName("게시물 좋아요 증가 - Optimistic")
    void likePostByOptimisticLock() throws InterruptedException {
        // given
        var post = savePost();

        int threadCount = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        // when
        IntStream.range(0, threadCount)
                .forEach(i -> executorService.submit(() -> {
                    try{
                        postWriteService.likePostByOptimisticLock(post.getId());
                    }finally {
                        latch.countDown();
                    }
                }));
        latch.await();

        // then
        var result = postRepository.findById(post.getId()).orElseThrow();
        assertEquals(1, result.getLikeCount());
    }

    private Post savePost() {
        var post = PostFixtureFactory.createPost(1L);
        return postRepository.save(post);
    }
}
