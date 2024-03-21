package pcy.study.sns.domain.member.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pcy.study.sns.util.MemberFixtureFactory;

class MemberTest {

    @Test
    @DisplayName("회원은 닉네임을 변경할 수 있다.")
    void changeNickname() {
        // given
        var member = MemberFixtureFactory.createMember();
        var expected = "name";

        // when
        member.changeNickname(expected);

        // then
        Assertions.assertEquals(expected, member.getNickname());
    }

    @Test
    @DisplayName("회원의 닉네임은 10자를 초과할 수 없다.")
    void changeNickname_maxLength() {
        // given
        var member = MemberFixtureFactory.createMember();
        var expected = "12345678910";

        // when
        // then
        Assertions.assertThrows(IllegalArgumentException.class, () -> member.changeNickname(expected));
    }
}
