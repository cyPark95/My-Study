package pcy.study.sns.util;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.randomizers.number.LongRandomizer;
import org.jeasy.random.randomizers.text.StringRandomizer;
import org.jeasy.random.randomizers.time.LocalDateRandomizer;
import pcy.study.sns.common.EasyRandomFactory;
import pcy.study.sns.domain.post.dto.PostDto;
import pcy.study.sns.domain.post.entity.Post;
import pcy.study.sns.domain.post.entity.PostLike;
import pcy.study.sns.domain.post.entity.Timeline;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static org.jeasy.random.FieldPredicates.*;

public class PostFixtureFactory extends EasyRandomFactory {

    public static Post createPost(Long memberId) {
        return get(memberId)
                .nextObject(Post.class);
    }

    public static List<Post> createPosts(int size, Long memberId) {
        EasyRandom easyRandom = get(memberId);

        return IntStream.range(0, size)
                .parallel()
                .mapToObj(i -> easyRandom.nextObject(Post.class))
                .toList();
    }

    public static EasyRandom get(Long memberId, LocalDate firstDate, LocalDate finalDate) {
        var param = POST_EASY_RANDOM_PARAMETERS
                .randomize(MEMBER_ID_PREDICATE, () -> memberId)
                .dateRange(firstDate, finalDate);

        return new EasyRandom(param);
    }

    public static PostDto createPostDto(Long memberId) {
        return new PostDto(
                new LongRandomizer().getRandomValue(),
                memberId,
                new StringRandomizer(20).getRandomValue(),
                new LocalDateRandomizer().getRandomValue(),
                0L
        );
    }

    public static Timeline createTimeline(Long memberId, Long postId) {
        return Timeline.builder()
                .memberId(memberId)
                .postId(postId)
                .build();
    }

    public static PostLike getPostLike(Long postId, long memberId) {
        return PostLike.builder()
                .postId(postId)
                .memberId(memberId)
                .build();
    }

    private static final Predicate<Field> LIKE_COUNT_PREDICATE = named("likeCount")
            .and(ofType(Long.class))
            .and(inClass(Post.class));

    private static final Predicate<Field> VERSION_PREDICATE = named("version")
            .and(ofType(Long.class))
            .and(inClass(Post.class));

    private static final EasyRandomParameters POST_EASY_RANDOM_PARAMETERS = EASY_RANDOM_BASE_PARAMETERS
            .randomize(LIKE_COUNT_PREDICATE, () -> 0L)
            .randomize(VERSION_PREDICATE, () -> 0L);

    public static final Predicate<Field> MEMBER_ID_PREDICATE = named("memberId")
            .and(ofType(Long.class))
            .and(inClass(Post.class));

    private static EasyRandom get(Long memberId) {
        var param = POST_EASY_RANDOM_PARAMETERS
                .randomize(MEMBER_ID_PREDICATE, () -> memberId);

        return new EasyRandom(param);
    }
}
