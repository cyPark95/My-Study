package pcy.study.api.domain.store.controller;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;
import pcy.study.api.domain.store.controller.model.StoreRegisterRequest;
import pcy.study.api.common.OpenApiTest;
import pcy.study.db.store.enums.StoreCategory;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static pcy.study.api.utility.StoreUtils.*;

class StoreOpenApiControllerTest extends OpenApiTest {

    @Test
    @DisplayName("가게 등록")
    void register() {
        // given
        StoreRegisterRequest request = createStoreRegisterRequest();
        String url = "/open-api/stores";

        // when
        ExtractableResponse<Response> response = post(request, url);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertEqualsStoreResponse(response.jsonPath());
    }

    @ParameterizedTest
    @MethodSource("invalidRegisterParameter")
    @DisplayName("가게 등록 - 잘못된 파라미터")
    void register_invalidParameter(StoreRegisterRequest request) {
        // given
        String url = "/open-api/stores";

        // when
        ExtractableResponse<Response> response = post(request, url);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    private static Stream<StoreRegisterRequest> invalidRegisterParameter() {
        return Stream.of(
                new StoreRegisterRequest("", STORE_ADDRESS, StoreCategory.COFFEE_TEA, STORE_THUMBNAIL_URL, STORE_MINIMUM_AMOUNT, STORE_MINIMUM_DELIVERY_AMOUNT, STORE_PHONE_NUMBER),
                new StoreRegisterRequest(null, "", StoreCategory.COFFEE_TEA, STORE_THUMBNAIL_URL, STORE_MINIMUM_AMOUNT, STORE_MINIMUM_DELIVERY_AMOUNT, STORE_PHONE_NUMBER),
                new StoreRegisterRequest(STORE_NAME, null, StoreCategory.COFFEE_TEA, STORE_THUMBNAIL_URL, STORE_MINIMUM_AMOUNT, STORE_MINIMUM_DELIVERY_AMOUNT, STORE_PHONE_NUMBER),
                new StoreRegisterRequest(STORE_NAME, STORE_ADDRESS, null, STORE_THUMBNAIL_URL, STORE_MINIMUM_AMOUNT, STORE_MINIMUM_DELIVERY_AMOUNT, STORE_PHONE_NUMBER),
                new StoreRegisterRequest(STORE_NAME, STORE_ADDRESS, StoreCategory.COFFEE_TEA, "", STORE_MINIMUM_AMOUNT, STORE_MINIMUM_DELIVERY_AMOUNT, STORE_PHONE_NUMBER),
                new StoreRegisterRequest(STORE_NAME, STORE_ADDRESS, StoreCategory.COFFEE_TEA, null, STORE_MINIMUM_AMOUNT, STORE_MINIMUM_DELIVERY_AMOUNT, STORE_PHONE_NUMBER),
                new StoreRegisterRequest(STORE_NAME, STORE_ADDRESS, StoreCategory.COFFEE_TEA, STORE_THUMBNAIL_URL, null, STORE_MINIMUM_DELIVERY_AMOUNT, STORE_PHONE_NUMBER),
                new StoreRegisterRequest(STORE_NAME, STORE_ADDRESS, StoreCategory.COFFEE_TEA, STORE_THUMBNAIL_URL, STORE_MINIMUM_AMOUNT, null, STORE_PHONE_NUMBER)
        );
    }
}
