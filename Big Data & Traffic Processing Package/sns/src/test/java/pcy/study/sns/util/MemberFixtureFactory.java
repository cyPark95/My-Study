package pcy.study.sns.util;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import pcy.study.sns.domain.member.entity.Member;

import static org.jeasy.random.FieldPredicates.*;

/**
 * EasyRandom
 * 자바 빈을 만들어주는 라이브러리
 */
public class MemberFixtureFactory {

    public static EasyRandom get() {
        var idPredicate = named("id")
                .and(ofType(Long.class))
                .and(inClass(Member.class));

        var param = new EasyRandomParameters()
                .excludeField(idPredicate)
                .stringLengthRange(5, 10);

        return new EasyRandom(param);
    }

    public static Member create() {
        return get()
                .nextObject(Member.class);
    }

    public static Member createWithId() {
        return new EasyRandom()
                .nextObject(Member.class);
    }
}
