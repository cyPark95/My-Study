package pcy.study.api.domain.user.business;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import pcy.study.api.domain.token.business.TokenBusiness;
import pcy.study.api.domain.token.controller.model.TokenResponse;
import pcy.study.api.domain.user.controller.model.UserLoginRequest;
import pcy.study.api.domain.user.controller.model.UserRegisterRequest;
import pcy.study.api.domain.user.controller.model.UserResponse;
import pcy.study.api.domain.user.converter.UserConverter;
import pcy.study.api.domain.user.service.UserService;
import pcy.study.db.user.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static pcy.study.api.utility.UserUtils.*;

@ExtendWith(MockitoExtension.class)
class UserBusinessTest {

    @InjectMocks
    private UserBusiness userBusiness;

    @Spy
    private UserConverter userConverter;
    @Mock
    private UserService userService;
    @Mock
    private TokenBusiness tokenBusiness;

    @Test
    @DisplayName("사용자 등록")
    void register() {
        // given
        UserRegisterRequest request = createUserRegisterRequest();

        User user = createUserWithId();
        when(userService.register(any(User.class))).thenReturn(user);

        // when
        UserResponse result = userBusiness.register(request);

        // then
        assertEqualsUserResponse(result);
    }

    @Test
    @DisplayName("로그인")
    void login() {
        // given
        UserLoginRequest request = createUserLoginRequest();

        User user = createUserWithId();
        when(userService.login(USER_EMAIL, USER_PASSWORD)).thenReturn(user);

        TokenResponse response = TokenResponse.builder().build();
        when(tokenBusiness.issueToken(USER_ID)).thenReturn(response);

        // when
        TokenResponse result = userBusiness.login(request);

        // then
        assertThat(result).isEqualTo(response);
    }

    @Test
    @DisplayName("사용자 조회")
    void info() {
        // given
        User user = createUserWithId();
        when(userService.getUserWithThrow(USER_ID)).thenReturn(user);

        // when
        UserResponse result = userBusiness.info(USER_ID);

        // then
        assertEqualsUserResponse(result);
    }
}
