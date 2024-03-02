package pcy.study.api.utility;

import io.restassured.path.json.JsonPath;
import org.springframework.test.util.ReflectionTestUtils;
import pcy.study.api.domain.user.controller.model.UserLoginRequest;
import pcy.study.api.domain.user.controller.model.UserRegisterRequest;
import pcy.study.api.domain.user.controller.model.UserResponse;
import pcy.study.db.user.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class UserUtils {

    public static final Long USER_ID = 1L;
    public static final String USER_NAME = "user name";
    public static final String USER_EMAIL = "email@gmail.com";
    public static final String USER_ADDRESS = "서울특별시 강남구 xxx";
    public static final String USER_PASSWORD = "password";

    private UserUtils() {
    }

    public static User createUser() {
        return User.builder()
                .name(USER_NAME)
                .email(USER_EMAIL)
                .address(USER_ADDRESS)
                .password(USER_PASSWORD)
                .build();
    }

    public static User createUserWithId() {
        User user = createUser();
        ReflectionTestUtils.setField(user, "id", USER_ID);
        return user;
    }

    public static UserRegisterRequest createUserRegisterRequest() {
        return new UserRegisterRequest(
                USER_NAME,
                USER_EMAIL,
                USER_ADDRESS,
                USER_PASSWORD
        );
    }

    public static UserLoginRequest createUserLoginRequest() {
        return new UserLoginRequest(USER_EMAIL, USER_PASSWORD);
    }

    public static void assertEqualsUserResponse(UserResponse response) {
        assertAll(
                () -> assertThat(response.id()).isEqualTo(USER_ID),
                () -> assertThat(response.email()).isEqualTo(USER_EMAIL),
                () -> assertThat(response.name()).isEqualTo(USER_NAME),
                () -> assertThat(response.address()).isEqualTo(USER_ADDRESS)
        );
    }

    public static void assertEqualsUserResponse(JsonPath jsonPath) {
        assertAll(
                () -> assertThat(jsonPath.getLong("body.id")).isEqualTo(USER_ID),
                () -> assertThat(jsonPath.getString("body.name")).isEqualTo(USER_NAME),
                () -> assertThat(jsonPath.getString("body.email")).isEqualTo(USER_EMAIL),
                () -> assertThat(jsonPath.getString("body.address")).isEqualTo(USER_ADDRESS)
        );
    }

    public static void assertEqualsTokenResponse(JsonPath jsonPath) {
        assertAll(
                () -> assertThat(jsonPath.getString("body.access_token")).isNotNull(),
                () -> assertThat(jsonPath.getString("body.access_token_expired_at")).isNotNull(),
                () -> assertThat(jsonPath.getString("body.refresh_token")).isNotNull(),
                () -> assertThat(jsonPath.getString("body.refresh_token_expired_at")).isNotNull()
        );
    }
}
