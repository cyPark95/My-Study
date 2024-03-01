package pcy.study.api.domain.storemenu.controller;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import pcy.study.api.domain.storemenu.controller.model.StoreMenuResponse;
import pcy.study.api.domain.user.ApiTest;
import pcy.study.api.utility.StoreMenuUtils;
import pcy.study.db.storemenu.StoreMenuRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static pcy.study.api.utility.StoreMenuUtils.createStoreMenu;
import static pcy.study.api.utility.StoreUtils.STORE_ID;

class StoreMenuApiControllerTest extends ApiTest {

    @Autowired
    private StoreMenuRepository storeMenuRepository;

    @Test
    @DisplayName("가게에 등록된 메뉴 조회")
    void search() {
        // given
        storeMenuRepository.save(createStoreMenu());

        String url = "/api/store-menus/search?storeId=" + STORE_ID;

        // when
        ExtractableResponse<Response> response = get(url);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        List<StoreMenuResponse> storeMenuResponses = response.jsonPath().getList("body", StoreMenuResponse.class);
        assertThat(storeMenuResponses.size()).isEqualTo(1);
        storeMenuResponses.forEach(StoreMenuUtils::assertEqualsStoreMenuResponse);
    }
}
