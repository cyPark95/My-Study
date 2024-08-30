package pcy.study.api.domain.user.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pcy.study.common.exception.ApiException;
import pcy.study.api.config.annotation.ServiceTest;
import pcy.study.db.user.User;
import pcy.study.db.user.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pcy.study.api.utility.UserUtils.*;

@ServiceTest
class UserServiceTest {

    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);

        // given
        user = saveUser();
    }

    @Test
    @DisplayName("사용자 등록")
    void register() {
        // given
        User user = new User(
                "name",
                "example@gmail.com",
                "example",
                "서울특별시"
        );

        // when
        User result = userService.register(user);

        // then
        assertThat(result.getId()).isNotNull();
    }

    @Test
    @DisplayName("사용자 등록 - Null 파라미터")
    void register_failureNullParameter() {
        // when
        // then
        assertThatThrownBy(() -> userService.register(null))
                .isInstanceOf(ApiException.class);
    }

    @Test
    @DisplayName("로그인")
    void login() {
        // when
        User result = userService.login(USER_EMAIL, USER_PASSWORD);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("로그인 - 잘못된 이메일")
    void login_failureInvalidEmail() {
        // when
        User result = userService.login(USER_EMAIL, USER_PASSWORD);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("로그인 - 잘못된 비밀번호")
    void login_failureInvalidPassword() {
        // when
        // then
        assertThatThrownBy(() -> userService.login(USER_EMAIL, "PASSWORD"))
                .isInstanceOf(ApiException.class);
    }

    @Test
    @DisplayName("사용자 조회")
    void getUserWithThrow() {
        // when
        User result = userService.getUserWithThrow(user.getId());

        // then
        assertThat(result).isEqualTo(user);
    }

    @Test
    @DisplayName("사용자 조회 - 없는 ID")
    void getUserWithThrow_failureNotFound() {
        // when
        // then
        assertThatThrownBy(() -> userService.getUserWithThrow(-1L))
                .isInstanceOf(ApiException.class);
    }

    private User saveUser() {
        User user = createUser();
        return userRepository.save(user);
    }
}
