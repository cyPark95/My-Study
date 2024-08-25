package pcy.study.storeadmin.domain.storeuser.controller;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import pcy.study.db.store.StoreRepository;
import pcy.study.storeadmin.common.ApiTest;
import pcy.study.storeadmin.domain.storeuser.controller.model.StoreUserResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static pcy.study.storeadmin.utility.StoreUserUtils.assertEqualsStoreUserResponse;
import static pcy.study.storeadmin.utility.StoreUtils.createStore;

class StoreUserApiControllerTest extends ApiTest {

    @Autowired
    private StoreRepository storeRepository;

    @Test
    @DisplayName("가맹점 사용자 등록")
    void register() {
        // given
        storeRepository.save(createStore());

        String url = "/api/store-users/me";

        // when
        ExtractableResponse<Response> response = get(url);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        StoreUserResponse result = response.jsonPath().getObject("$", StoreUserResponse.class);
        assertEqualsStoreUserResponse(result);
    }
}
