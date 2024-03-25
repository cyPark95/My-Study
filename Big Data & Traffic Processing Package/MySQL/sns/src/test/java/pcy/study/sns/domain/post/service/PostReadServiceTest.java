package pcy.study.sns.domain.post.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import pcy.study.sns.common.IntegrationTest;
import pcy.study.sns.domain.post.dto.DailyPostCountRequest;
import pcy.study.sns.domain.post.entity.Post;
import pcy.study.sns.domain.post.repository.PostRepository;
import pcy.study.sns.util.CursorRequest;
import pcy.study.sns.util.PostAssertUtil;
import pcy.study.sns.util.PostFixtureFactory;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.*;

@IntegrationTest
class PostReadServiceTest {

    private static final Long memberId = -1L;

    @Autowired
    private PostReadService postReadService;

    @Autowired
    private PostRepository postRepository;

    @Test
    @DisplayName("날짜별 게시물 개수 조회")
    void getDailyPostCounts() {
        // given
        var size = 10;
        var now = LocalDate.now();
        var easyRandom = PostFixtureFactory.get(memberId, now, now);
        var posts = IntStream.range(0, size)
                .mapToObj(i -> easyRandom.nextObject(Post.class))
                .toList();
        postRepository.bulkInsert(posts);

        var request = new DailyPostCountRequest(memberId, now, now.plusDays(7));

        // when
        var results = postReadService.getDailyPostCounts(request);

        // then
        assertEquals(1, results.size());

        var dailyPostCount = results.get(0);
        assertEquals(size, dailyPostCount.postCount());
        assertEquals(now, dailyPostCount.date());
    }

    @Test
    @DisplayName("ID로 게시물 조회")
    void getPost() {
        // given
        var post = savePost();

        // when
        var result = postReadService.getPost(post.getId());

        // then
        assertAll(
                () -> assertEquals(post.getId(), result.getId()),
                () -> assertEquals(post.getMemberId(), result.getMemberId()),
                () -> assertEquals(post.getContents(), result.getContents()),
                () -> assertEquals(post.getCreatedDate(), result.getCreatedDate())
        );
    }

    @Test
    @DisplayName("존재하지 않는 ID로 게시물 조회")
    void getPost_NotFound() {
        // when
        // then
        assertThrows(
                NoSuchElementException.class,
                () -> postReadService.getPost(-1L)
        );
    }

    @Test
    @DisplayName("게시물 목록 조회 - List")
    void getPostList() {
        // given
        var size = 10;
        var posts = PostFixtureFactory.createPosts(size, memberId);
        var ids = posts.stream()
                .map(post -> postRepository.save(post).getId())
                .toList();

        // when
        var results = postReadService.getPosts(ids);

        // then
        for (int i = 0; i < size; i++) {
            var id = ids.get(i);
            var post = posts.get(i);
            var result = results.get(i);
            assertAll(
                    () -> assertEquals(id, result.id()),
                    () -> PostAssertUtil.assertEqualsPost(post, result)
            );
        }
    }

    @Test
    @DisplayName("게시물 목록 조회 - Page")
    void getPostPage() {
        // given
        var size = 5;
        var posts = PostFixtureFactory.createPosts(size + 1, memberId);
        var ids = posts.stream()
                .map(post -> postRepository.save(post).getId())
                .toList();

        var pageReqeust = PageRequest.of(
                0,
                size,
                Sort.by("createdDate").descending()
                        .and(Sort.by("id").descending())
        );

        // when
        var results = postReadService.getPosts(memberId, pageReqeust);

        // then
        assertEquals(size + 1, results.getTotalElements());
        assertEquals(0, results.getNumber());
        assertEquals(size, results.getNumberOfElements());

        for (int i = 0; i < size; i++) {
            var id = ids.get(i);
            var post = posts.get(i);
            var result = results.getContent().get(i);
            assertAll(
                    () -> assertEquals(id, result.id()),
                    () -> PostAssertUtil.assertEqualsPost(post, result)
            );
        }
    }

    @Test
    @DisplayName("게시물 목록 조회 - PageCursor")
    void fetPostPageCursor() {
        // given
        var size = 5;
        var posts = PostFixtureFactory.createPosts(size + 1, memberId);
        var maxId = posts.stream()
                .mapToLong(post -> postRepository.save(post).getId())
                .max()
                .orElseThrow();

        var cursorReqeust = new CursorRequest(maxId + 1, size);

        // when
        var results = postReadService.getPosts(memberId, cursorReqeust);

        // then
        assertTrue(results.nextCursorRequest().hasKey());
        assertEquals(size, results.body().size());

        for (int i = 0; i < size; i++) {
            PostAssertUtil.assertEqualsPost(posts.get(size - i), results.body().get(i));
        }
    }

    @Test
    @DisplayName("회원 ID 목록으로 게시물 목록 조회 - PageCursor")
    void fetPostPageCursorByMemberIds() {
        // given
        int size = 10;
        var posts = LongStream.range(0, size)
                .mapToObj(PostFixtureFactory::createPost)
                .toList();
        var maxId = posts.stream()
                .mapToLong(post -> postRepository.save(post).getId())
                .max()
                .orElseThrow();

        var memberIds = LongStream.range(0, size / 2)
                .boxed()
                .toList();
        var cursorReqeust = new CursorRequest(maxId + 1, size);

        // when
        var results = postReadService.getPosts(memberIds, cursorReqeust);

        // then
        assertTrue(results.nextCursorRequest().hasKey());
    }

    private Post savePost() {
        var post = PostFixtureFactory.createPost(memberId);
        return postRepository.save(post);
    }
}
