package pcy.study.sns.domain.post.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pcy.study.sns.common.IntegrationTest;
import pcy.study.sns.domain.post.dto.PostCommand;
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
        var post = PostFixtureFactory.createPost(1L);
        var newPost = postRepository.save(post);

        // when
        postWriteService.likePostByOptimisticLock(newPost.getId());

        // then
        var result = postRepository.findById(newPost.getId(), false)
                .orElseThrow();
        assertEquals(1, result.getLikeCount());
    }

    @Test
    @DisplayName("게시물 좋아요 증가 - Optimistic")
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void likePostByOptimisticLock() throws InterruptedException {
        // given
        var post = PostFixtureFactory.createPost(1L);
        var newPost = postRepository.save(post);

        int threadCount = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        // when
        IntStream.range(0, threadCount)
                .forEach(i -> executorService.submit(() -> {
                    try{
                        postWriteService.likePostByOptimisticLock(newPost.getId());
                    }finally {
                        latch.countDown();
                    }
                }));
        latch.await();

        // then
        var result = postRepository.findById(newPost.getId(), false)
                .orElseThrow();
        assertEquals(1, result.getLikeCount());
    }
}
