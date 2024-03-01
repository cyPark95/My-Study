package pcy.study.api.domain.storemenu.controller;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;
import pcy.study.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import pcy.study.api.domain.user.OpenApiTest;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static pcy.study.api.utility.StoreMenuUtils.*;
import static pcy.study.api.utility.StoreUtils.STORE_ID;
import static pcy.study.api.utility.StoreUtils.STORE_THUMBNAIL_URL;

class StoreMenuOpenApiControllerTest extends OpenApiTest {

    @Test
    @DisplayName("가게 메뉴 등록")
    void register() {
        // given
        StoreMenuRegisterRequest request = createStoreMenuRegisterRequest();
        String url = "/open-api/store-menus";

        // when
        ExtractableResponse<Response> response = post(request, url);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertEqualsStoreMenuResponse(response.jsonPath());
    }

    @ParameterizedTest
    @MethodSource("invalidRegisterParameter")
    @DisplayName("가게 메뉴 등록 - 잘못된 파라미터")
    void register_invalidParameter(StoreMenuRegisterRequest request) {
        // given
        String url = "/open-api/store-menus";

        // when
        ExtractableResponse<Response> response = post(request, url);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    private static Stream<StoreMenuRegisterRequest> invalidRegisterParameter() {
        return Stream.of(
                new StoreMenuRegisterRequest(null, STORE_MENU_NAME, STORE_MENU_AMOUNT, STORE_THUMBNAIL_URL),
                new StoreMenuRegisterRequest(STORE_ID, "", STORE_MENU_AMOUNT, STORE_THUMBNAIL_URL),
                new StoreMenuRegisterRequest(STORE_ID, null, STORE_MENU_AMOUNT, STORE_THUMBNAIL_URL),
                new StoreMenuRegisterRequest(STORE_ID, STORE_MENU_NAME, null, STORE_THUMBNAIL_URL),
                new StoreMenuRegisterRequest(STORE_ID, STORE_MENU_NAME, STORE_MENU_AMOUNT, ""),
                new StoreMenuRegisterRequest(STORE_ID, STORE_MENU_NAME, STORE_MENU_AMOUNT, null)
        );
    }
}
