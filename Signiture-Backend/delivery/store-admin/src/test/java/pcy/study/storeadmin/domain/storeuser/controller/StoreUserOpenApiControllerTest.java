package pcy.study.storeadmin.domain.storeuser.controller;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import pcy.study.db.store.StoreRepository;
import pcy.study.db.storeuser.enums.StoreUserRole;
import pcy.study.storeadmin.common.OpenApiTest;
import pcy.study.storeadmin.domain.storeuser.controller.model.StoreUserRegisterRequest;
import pcy.study.storeadmin.domain.storeuser.controller.model.StoreUserResponse;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static pcy.study.storeadmin.utility.StoreUserUtils.*;
import static pcy.study.storeadmin.utility.StoreUtils.STORE_NAME;
import static pcy.study.storeadmin.utility.StoreUtils.createStore;

class StoreUserOpenApiControllerTest extends OpenApiTest {

    @Autowired
    private StoreRepository storeRepository;

    @Test
    @DisplayName("가맹점 사용자 등록")
    void register() {
        // given
        storeRepository.save(createStore());

        StoreUserRegisterRequest request = createStoreUserRegisterRequest();
        String url = "/open-api/store-users";

        // when
        ExtractableResponse<Response> response = post(request, url);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        StoreUserResponse result = response.jsonPath().getObject("$", StoreUserResponse.class);
        assertEqualsStoreUserResponse(result);
    }

    @ParameterizedTest
    @MethodSource("invalidRegisterParameter")
    @DisplayName("가맹점 사용자 등록 - 잘못된 파라미터")
    void register_invalidParameter(StoreUserRegisterRequest request) {
        // given
        String url = "/open-api/store-users";

        // when
        ExtractableResponse<Response> response = post(request, url);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    private static Stream<StoreUserRegisterRequest> invalidRegisterParameter() {
        return Stream.of(
                new StoreUserRegisterRequest("", STORE_USER_EMAIL, STORE_USER_PASSWORD, StoreUserRole.USER),
                new StoreUserRegisterRequest(null, STORE_USER_EMAIL, STORE_USER_PASSWORD, StoreUserRole.USER),
                new StoreUserRegisterRequest(STORE_NAME, "", STORE_USER_PASSWORD, StoreUserRole.USER),
                new StoreUserRegisterRequest(STORE_NAME, "email", STORE_USER_PASSWORD, StoreUserRole.USER),
                new StoreUserRegisterRequest(STORE_NAME, null, STORE_USER_PASSWORD, StoreUserRole.USER),
                new StoreUserRegisterRequest(STORE_NAME, STORE_USER_EMAIL, "", StoreUserRole.USER),
                new StoreUserRegisterRequest(STORE_NAME, STORE_USER_EMAIL, null, StoreUserRole.USER),
                new StoreUserRegisterRequest(STORE_NAME, STORE_USER_EMAIL, STORE_USER_PASSWORD, null)
        );
    }
}
