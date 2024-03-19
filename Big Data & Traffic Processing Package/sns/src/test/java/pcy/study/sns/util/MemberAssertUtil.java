package pcy.study.sns.util;

import pcy.study.sns.domain.member.dto.MemberDto;
import pcy.study.sns.domain.member.dto.MemberNicknameHistoryDto;
import pcy.study.sns.domain.member.entity.Member;
import pcy.study.sns.domain.member.entity.MemberNicknameHistory;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MemberAssertUtil {

    public static void assertEqualsMember(Member member, MemberDto result) {
        assertAll(
                () -> assertEquals(member.getId(), result.id()),
                () -> assertEquals(member.getEmail(), result.email()),
                () -> assertEquals(member.getNickname(), result.nickname()),
                () -> assertEquals(member.getBirthday(), result.birthday())
        );
    }

    public static void assertEqualsMemberNicknameHistory(MemberNicknameHistory member, MemberNicknameHistoryDto result) {
        assertAll(
                () -> assertEquals(member.getId(), result.id()),
                () -> assertEquals(member.getMemberId(), result.memberId()),
                () -> assertEquals(member.getNickname(), result.nickname())
        );
    }
}
