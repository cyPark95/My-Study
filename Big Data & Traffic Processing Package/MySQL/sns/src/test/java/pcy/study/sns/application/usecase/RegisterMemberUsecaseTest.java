package pcy.study.sns.application.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pcy.study.sns.common.IntegrationTest;
import pcy.study.sns.domain.member.dto.MemberRegisterCommand;

import java.time.LocalDate;

@IntegrationTest
@DisplayName("회원을 등록한다.")
class RegisterMemberUsecaseTest {

    @Autowired
    private RegisterMemberUsecase registerMemberUsecase;

    @Test
    void execute() {
        // given
        var command = new MemberRegisterCommand(
                "email",
                "password",
                "nickname",
                LocalDate.now()
        );

        // when
        var result = registerMemberUsecase.execute(command);

        // then
        Assertions.assertNotNull(result.id());

        Assertions.assertEquals(command.email(), result.email());
        Assertions.assertEquals(command.nickname(), result.nickname());
        Assertions.assertEquals(command.birthday(), result.birthday());
    }
}
