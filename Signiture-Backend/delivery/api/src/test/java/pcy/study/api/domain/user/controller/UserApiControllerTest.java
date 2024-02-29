package pcy.study.api.domain.user.controller;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pcy.study.api.domain.user.ApiTest;

import static pcy.study.api.utility.UserUtils.assertEqualsUserResponse;

class UserApiControllerTest extends ApiTest {

    @Test
    @DisplayName("사용자 정보 조회")
    void me() {
        // given
        String url = "/api/users/me";

        // when
        ExtractableResponse<Response> response = get(url);

        // then
        assertEqualsUserResponse(response.jsonPath());
    }
}
