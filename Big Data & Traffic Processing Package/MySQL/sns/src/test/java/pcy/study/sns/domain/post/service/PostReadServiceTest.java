package pcy.study.sns.domain.post.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import pcy.study.sns.common.DatabaseClearExtension;
import pcy.study.sns.domain.common.entity.BaseEntity;
import pcy.study.sns.domain.post.dto.DailyPostCountRequest;
import pcy.study.sns.domain.post.entity.Post;
import pcy.study.sns.domain.post.repository.PostJdbcRepository;
import pcy.study.sns.domain.post.repository.PostRepository;
import pcy.study.sns.util.CursorRequest;
import pcy.study.sns.util.PostAssertUtil;
import pcy.study.sns.util.PostFixtureFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(DatabaseClearExtension.class)
@SpringBootTest
class PostReadServiceTest {

    private static final Long MEMBER_ID = -1L;

    @Autowired
    private PostReadService postReadService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostJdbcRepository postJdbcRepository;

    @Test
    @DisplayName("날짜별 게시물 개수 조회")
    void getDailyPostCounts() {
        // given
        var size = 10;
        var now = LocalDate.now();
        var easyRandom = PostFixtureFactory.get(MEMBER_ID, now, now);
        var posts = IntStream.range(0, size)
                .mapToObj(i -> easyRandom.nextObject(Post.class))
                .toList();
        postJdbcRepository.bulkInsert(posts);

        var request = new DailyPostCountRequest(MEMBER_ID, now, now.plusDays(7));

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
        PostAssertUtil.assertEqualsPost(post, result);
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
    @DisplayName("게시물 ID 목록으로 게시물 목록 조회")
    void getPostList() {
        // given
        var size = 10;
        var posts = savePosts(size);
        var ids = posts.stream()
                .map(Post::getId)
                .toList();

        // when
        var results = postReadService.getPosts(ids);

        // then
        for (int i = 0; i < size; i++) {
            PostAssertUtil.assertEqualsPost(posts.get(i), results.get(i));
        }
    }

    @Test
    @DisplayName("게시물 목록 조회 - Page")
    void getPostPage() {
        // given
        var size = 5;
        var posts = savePosts(size);

        var pageReqeust = PageRequest.of(0, size);

        // when
        var results = postReadService.getPosts(MEMBER_ID, pageReqeust);

        // then
        assertEquals(size + 1, results.getTotalElements());
        assertEquals(2, results.getTotalPages());
        assertEquals(0, results.getNumber());
        assertEquals(size, results.getNumberOfElements());

        for (int i = 0; i < size; i++) {
            PostAssertUtil.assertEqualsPost(posts.get(i), results.getContent().get(i));
        }
    }

    @Test
    @DisplayName("게시물 목록 조회 - PageCursor")
    void fetPostPageCursor() {
        // given
        var size = 5;
        var posts = savePosts(size);
        var key = getMaxId(posts) + 1;

        var cursorReqeust = new CursorRequest(key, size);

        // when
        var results = postReadService.getPosts(MEMBER_ID, cursorReqeust);

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
        var size = 10;
        var posts = LongStream.range(0, size)
                .mapToObj(PostFixtureFactory::createPost)
                .toList();
        var key = getMaxId(posts) + 1;

        var memberIds = LongStream.range(0, size / 2)
                .boxed()
                .toList();
        var cursorReqeust = new CursorRequest(key + 1, size);

        // when
        var results = postReadService.getPosts(memberIds, cursorReqeust);

        // then
        assertTrue(results.nextCursorRequest().hasKey());
    }

    private Post savePost() {
        var post = PostFixtureFactory.createPost(MEMBER_ID);
        return postRepository.save(post);
    }

    private List<Post> savePosts(int size) {
        return PostFixtureFactory.createPosts(size + 1, MEMBER_ID).stream()
                .map(postRepository::save)
                .toList();
    }

    private Long getMaxId(List<Post> posts) {
        return posts.stream()
                .mapToLong(BaseEntity::getId)
                .max()
                .orElseThrow();
    }
}
