package pcy.study.sns.util;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import pcy.study.sns.domain.member.entity.Member;

/**
 * EasyRandom
 * 자바 빈을 만들어주는 라이브러리
 */
public class MemberFixtureFactory {

    public static Member create() {
        return new EasyRandom()
                .nextObject(Member.class);
    }

    public static Member create(Long seed) {
        var param = new EasyRandomParameters().seed(seed);
        return new EasyRandom(param)
                .nextObject(Member.class);
    }
}
