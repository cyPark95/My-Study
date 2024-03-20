package pcy.study.sns.util;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import pcy.study.sns.domain.member.entity.Member;
import pcy.study.sns.domain.member.entity.MemberNicknameHistory;

import java.util.List;
import java.util.stream.IntStream;

import static org.jeasy.random.FieldPredicates.*;

/**
 * EasyRandom
 * 자바 빈을 만들어주는 라이브러리
 */
public class MemberFixtureFactory {

    public static Member create() {
        return get()
                .nextObject(Member.class);
    }

    public static List<Member> createMembers(int size) {
        EasyRandom easyRandom = get();
        return IntStream.range(0, size)
                .mapToObj(i -> easyRandom.nextObject(Member.class))
                .toList();
    }

    public static Member createWithoutId() {
        return getWithoutId()
                .nextObject(Member.class);
    }

    public static List<Member> createMembersWithoutId(int size) {
        EasyRandom easyRandom = getWithoutId();
        return IntStream.range(0, size)
                .mapToObj(i -> easyRandom.nextObject(Member.class))
                .toList();
    }

    public static MemberNicknameHistory createMemberNicknameHistory(Long memberId) {
        return get(memberId)
                .nextObject(MemberNicknameHistory.class);
    }

    public static List<MemberNicknameHistory> createMemberNicknameHistories(int size, Long memberId) {
        EasyRandom easyRandom = get(memberId);

        return IntStream.range(0, size)
                .parallel()
                .mapToObj(i -> easyRandom.nextObject(MemberNicknameHistory.class))
                .toList();
    }

    private static EasyRandom get() {
        var param = new EasyRandomParameters()
                .stringLengthRange(5, 10);

        return new EasyRandom(param);
    }

    private static EasyRandom getWithoutId() {
        var idPredicate = named("id")
                .and(ofType(Long.class))
                .and(inClass(Member.class));

        var param = new EasyRandomParameters()
                .excludeField(idPredicate)
                .stringLengthRange(5, 10);

        return new EasyRandom(param);
    }

    private static EasyRandom get(Long memberId) {
        var memberIdPredicate = named("memberId")
                .and(ofType(Long.class))
                .and(inClass(MemberNicknameHistory.class));

        var param = new EasyRandomParameters()
                .randomize(memberIdPredicate, () -> memberId)
                .stringLengthRange(5, 10);

        return new EasyRandom(param);
    }
}
