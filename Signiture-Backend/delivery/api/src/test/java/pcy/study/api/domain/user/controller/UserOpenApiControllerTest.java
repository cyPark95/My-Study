package pcy.study.api.domain.user.controller;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import pcy.study.api.domain.user.OpenApiTest;
import pcy.study.api.domain.user.controller.model.UserLoginRequest;
import pcy.study.api.domain.user.controller.model.UserRegisterRequest;
import pcy.study.db.user.UserRepository;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static pcy.study.api.utility.UserUtils.*;

class UserOpenApiControllerTest extends OpenApiTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("사용자 등록")
    void register() {
        // given
        UserRegisterRequest request = createUserRegisterRequest();
        String url = "/open-api/users";

        // when
        ExtractableResponse<Response> response = post(request, url);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertEqualsUserResponse(response.jsonPath());
    }

    @ParameterizedTest
    @MethodSource("invalidRegisterParameter")
    @DisplayName("사용자 등록 - 잘못된 파라미터")
    void register_invalidParameter(UserRegisterRequest request) {
        // given
        String url = "/open-api/users";

        // when
        ExtractableResponse<Response> response = post(request, url);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    private static Stream<UserRegisterRequest> invalidRegisterParameter() {
        return Stream.of(
                new UserRegisterRequest("", USER_EMAIL, USER_ADDRESS, USER_PASSWORD),
                new UserRegisterRequest(null, USER_EMAIL, USER_ADDRESS, USER_PASSWORD),
                new UserRegisterRequest(USER_NAME, "", USER_ADDRESS, USER_PASSWORD),
                new UserRegisterRequest(USER_NAME, null, USER_ADDRESS, USER_PASSWORD),
                new UserRegisterRequest(USER_NAME, USER_EMAIL, "", USER_PASSWORD),
                new UserRegisterRequest(USER_NAME, USER_EMAIL, null, USER_PASSWORD),
                new UserRegisterRequest(USER_NAME, USER_EMAIL, USER_ADDRESS, ""),
                new UserRegisterRequest(USER_NAME, USER_EMAIL, USER_ADDRESS, null)
        );
    }

    @Test
    @DisplayName("로그인")
    void login() {
        // given
        userRepository.save(createUser());

        UserLoginRequest request = createUserLoginRequest();
        String url = "/open-api/users/login";

        // when
        ExtractableResponse<Response> response = post(request, url);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertEqualsTokenResponse(response.jsonPath());
    }

    @ParameterizedTest
    @MethodSource("invalidLoginParameter")
    @DisplayName("로그인 - 잘못된 파라미터")
    void login_invalidParameter(UserLoginRequest request) {
        // given
        String url = "/open-api/users/login";

        // when
        ExtractableResponse<Response> response = post(request, url);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    private static Stream<UserLoginRequest> invalidLoginParameter() {
        return Stream.of(
                new UserLoginRequest(null, USER_PASSWORD),
                new UserLoginRequest("", USER_PASSWORD),
                new UserLoginRequest(USER_EMAIL, null),
                new UserLoginRequest(USER_EMAIL, "")
        );
    }

    @ParameterizedTest
    @MethodSource("invalidLoginEmailOrPassword")
    @DisplayName("로그인 - 잘못된 아이디 or 비밀번호")
    void login_invalidIdOrPassword(UserLoginRequest request) {
        // given
        String url = "/open-api/users/login";

        // when
        ExtractableResponse<Response> response = post(request, url);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    private static Stream<UserLoginRequest> invalidLoginEmailOrPassword() {
        return Stream.of(
                new UserLoginRequest("EMAIL", USER_PASSWORD),
                new UserLoginRequest(USER_EMAIL, "PASSWORD"),
                new UserLoginRequest("EMAIL", "PASSWORD")
        );
    }
}
