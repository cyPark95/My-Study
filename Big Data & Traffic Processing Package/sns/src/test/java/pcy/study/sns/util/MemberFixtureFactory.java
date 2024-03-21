package pcy.study.sns.util;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.randomizers.number.LongRandomizer;
import org.jeasy.random.randomizers.text.StringRandomizer;
import org.jeasy.random.randomizers.time.LocalDateRandomizer;
import pcy.study.sns.common.EasyRandomFactory;
import pcy.study.sns.domain.member.dto.MemberDto;
import pcy.study.sns.domain.member.entity.Member;
import pcy.study.sns.domain.member.entity.MemberNicknameHistory;

import java.util.List;
import java.util.stream.IntStream;

import static org.jeasy.random.FieldPredicates.*;

public class MemberFixtureFactory extends EasyRandomFactory {

    private static final EasyRandomParameters MEMBER_EASY_RANDOM_PARAMETERS = EASY_RANDOM_BASE_PARAMETERS
            .stringLengthRange(5, 20);

    public static Member createMember() {
        return get(MEMBER_EASY_RANDOM_PARAMETERS)
                .nextObject(Member.class);
    }

    public static List<Member> createMembers(int size) {
        EasyRandom easyRandom = get(MEMBER_EASY_RANDOM_PARAMETERS);
        return IntStream.range(0, size)
                .mapToObj(i -> easyRandom.nextObject(Member.class))
                .toList();
    }

    public static List<MemberNicknameHistory> createMemberNicknameHistories(int size, Long memberId) {
        EasyRandom easyRandom = get(memberId);

        return IntStream.range(0, size)
                .parallel()
                .mapToObj(i -> easyRandom.nextObject(MemberNicknameHistory.class))
                .toList();
    }

    public static MemberDto createMemberDto() {
        return new MemberDto(
                new LongRandomizer().getRandomValue(),
                new StringRandomizer(20).getRandomValue(),
                new StringRandomizer(20).getRandomValue(),
                new LocalDateRandomizer().getRandomValue()
        );
    }

    private static EasyRandom get(Long memberId) {
        var memberIdPredicate = named("memberId")
                .and(ofType(Long.class))
                .and(inClass(MemberNicknameHistory.class));

        var param = MEMBER_EASY_RANDOM_PARAMETERS
                .randomize(memberIdPredicate, () -> memberId);

        return new EasyRandom(param);
    }
}
