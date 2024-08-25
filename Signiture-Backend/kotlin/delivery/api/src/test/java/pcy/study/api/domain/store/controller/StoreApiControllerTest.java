package pcy.study.api.domain.store.controller;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import pcy.study.api.domain.store.controller.model.StoreResponse;
import pcy.study.api.common.ApiTest;
import pcy.study.api.utility.StoreUtils;
import pcy.study.db.store.StoreRepository;
import pcy.study.db.store.enums.StoreCategory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static pcy.study.api.utility.StoreUtils.createStore;

class StoreApiControllerTest extends ApiTest {

    @Autowired
    private StoreRepository storeRepository;

    @Test
    @DisplayName("카테고리 별 가게 조회")
    void search() {
        // given
        storeRepository.save(createStore());

        String url = "/api/stores/search?category=" + StoreCategory.COFFEE_TEA;

        // when
        ExtractableResponse<Response> response = get(url);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());

        List<StoreResponse> storeResponses = response.jsonPath().getList("body", StoreResponse.class);
        assertThat(storeResponses.size()).isEqualTo(1);
        storeResponses.forEach(StoreUtils::assertEqualsStoreResponse);
    }
}
