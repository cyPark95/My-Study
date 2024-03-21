package pcy.study.sns.domain.post;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;
import pcy.study.sns.domain.post.entity.Post;
import pcy.study.sns.domain.post.repository.PostRepository;
import pcy.study.sns.util.PostFixtureFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class PostBulkInsertTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    @Disabled
    void bulkInsert() {
        EasyRandom easyRandom = PostFixtureFactory.get(
                1L,
                LocalDate.of(2023, 2, 14),
                LocalDate.of(2024, 3, 14)
        );

        var stopWatch = new StopWatch();
        stopWatch.start();

        List<Post> posts = IntStream.range(0, 1_000_000)
                .parallel()
                .mapToObj(i -> easyRandom.nextObject(Post.class))
                .toList();

        stopWatch.stop();
        System.out.println("객체 생성 시간: " + stopWatch.getTotalTimeSeconds());
        
        var querystopWatch = new StopWatch();
        querystopWatch.start();
        postRepository.bulkInsert(posts);

        querystopWatch.stop();
        System.out.println("DB 인서트 시간: " + querystopWatch.getTotalTimeSeconds());
    }
}
