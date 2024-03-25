package pcy.study.sns.util;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import pcy.study.sns.common.EasyRandomFactory;
import pcy.study.sns.domain.post.entity.Post;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

import static org.jeasy.random.FieldPredicates.*;

public class PostFixtureFactory extends EasyRandomFactory {

    public static Post createPost(Long memberId) {
        var idPredicate = named("id")
                .and(ofType(Long.class))
                .and(inClass(Post.class));

        var memberIdPredicate = named("memberId")
                .and(ofType(Long.class))
                .and(inClass(Post.class));

        var likeCountPredicate = named("likeCount")
                .and(ofType(Long.class))
                .and(inClass(Post.class));

        var versionPredicate = named("version")
                .and(ofType(Long.class))
                .and(inClass(Post.class));

        var param = new EasyRandomParameters()
                .excludeField(idPredicate)
                .randomize(likeCountPredicate, () -> 0L)
                .randomize(versionPredicate, () -> 0L)
                .randomize(memberIdPredicate, () -> memberId);

        return new EasyRandom(param)
                .nextObject(Post.class);
    }

    public static List<Post> createPosts(int size, Long memberId) {

        var idPredicate = named("id")
                .and(ofType(Long.class))
                .and(inClass(Post.class));

        var memberIdPredicate = named("memberId")
                .and(ofType(Long.class))
                .and(inClass(Post.class));

        var likeCountPredicate = named("likeCount")
                .and(ofType(Long.class))
                .and(inClass(Post.class));

        var versionPredicate = named("version")
                .and(ofType(Long.class))
                .and(inClass(Post.class));

        var param = new EasyRandomParameters()
                .excludeField(idPredicate)
                .randomize(likeCountPredicate, () -> 0L)
                .randomize(versionPredicate, () -> 0L)
                .randomize(memberIdPredicate, () -> memberId);

        EasyRandom easyRandom = new EasyRandom(param);
        return IntStream.range(0, size)
                .parallel()
                .mapToObj(i -> easyRandom.nextObject(Post.class))
                .toList();

    }

    public static EasyRandom get(Long memberId, LocalDate firstDate, LocalDate finalDate) {
        var idPredicate = named("id")
                .and(ofType(Long.class))
                .and(inClass(Post.class));

        var memberIdPredicate = named("memberId")
                .and(ofType(Long.class))
                .and(inClass(Post.class));

        var likeCountPredicate = named("likeCount")
                .and(ofType(Long.class))
                .and(inClass(Post.class));

        var versionPredicate = named("version")
                .and(ofType(Long.class))
                .and(inClass(Post.class));

        var param = new EasyRandomParameters()
                .excludeField(idPredicate)
                .dateRange(firstDate, finalDate)
                .randomize(likeCountPredicate, () -> 0L)
                .randomize(versionPredicate, () -> 0L)
                .randomize(memberIdPredicate, () -> memberId);

        return new EasyRandom(param);
    }
}
