package pcy.study.api.utility;

import io.restassured.path.json.JsonPath;
import org.springframework.test.util.ReflectionTestUtils;
import pcy.study.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import pcy.study.api.domain.storemenu.controller.model.StoreMenuResponse;
import pcy.study.db.store.Store;
import pcy.study.db.storemenu.StoreMenu;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static pcy.study.api.utility.StoreUtils.STORE_ID;
import static pcy.study.api.utility.StoreUtils.STORE_THUMBNAIL_URL;

public class StoreMenuUtils {

    public static final Long STORE_MENU_ID = 1L;
    public static final String STORE_MENU_NAME = "storeMenu name";
    public static final BigDecimal STORE_MENU_AMOUNT = BigDecimal.valueOf(10000.0);
    public static final String STORE_MENU_THUMBNAIL_URL = "https://www.thumbnail.xxx";
    public static final int STORE_MENU_LIKE_COUNT = 0;
    public static final int STORE_MENU_SEQUENCE = 0;

    private StoreMenuUtils() {
    }

    public static StoreMenu createStoreMenu() {
        Store store = StoreUtils.createStoreWithId();
        return new StoreMenu(
                store,
                STORE_MENU_NAME,
                STORE_MENU_AMOUNT,
                STORE_MENU_THUMBNAIL_URL
        );
    }

    public static StoreMenu createStoreMenuWithId() {
        StoreMenu storeMenu = createStoreMenu();
        ReflectionTestUtils.setField(storeMenu, "id", STORE_MENU_ID);
        return storeMenu;
    }

    public static StoreMenuRegisterRequest createStoreMenuRegisterRequest() {
        return new StoreMenuRegisterRequest(
                STORE_ID,
                STORE_MENU_NAME,
                STORE_MENU_AMOUNT,
                STORE_THUMBNAIL_URL
        );
    }

    public static void assertEqualsStoreMenuResponse(StoreMenuResponse response) {
        assertAll(
                () -> assertThat(response.id()).isEqualTo(STORE_MENU_ID),
                () -> assertThat(response.storeId()).isEqualTo(STORE_ID),
                () -> assertThat(response.name()).isEqualTo(STORE_MENU_NAME),
                () -> assertThat(response.amount()).isEqualTo(STORE_MENU_AMOUNT),
                () -> assertThat(response.thumbnailUrl()).isEqualTo(STORE_MENU_THUMBNAIL_URL),
                () -> assertThat(response.likeCount()).isEqualTo(STORE_MENU_LIKE_COUNT),
                () -> assertThat(response.sequence()).isEqualTo(STORE_MENU_SEQUENCE)
        );
    }

    public static void assertEqualsStoreMenuResponse(JsonPath jsonPath) {
        assertAll(
                () -> assertThat(jsonPath.getLong("body.id")).isEqualTo(STORE_MENU_ID),
                () -> assertThat(jsonPath.getLong("body.store_id")).isEqualTo(STORE_ID),
                () -> assertThat(jsonPath.getString("body.name")).isEqualTo(STORE_MENU_NAME),
                () -> assertThat(jsonPath.getObject("body.amount", BigDecimal.class)).isEqualTo(STORE_MENU_AMOUNT),
                () -> assertThat(jsonPath.getString("body.thumbnail_url")).isEqualTo(STORE_MENU_THUMBNAIL_URL),
                () -> assertThat(jsonPath.getInt("body.like_count")).isEqualTo(STORE_MENU_LIKE_COUNT),
                () -> assertThat(jsonPath.getInt("body.sequence")).isEqualTo(STORE_MENU_SEQUENCE)
        );
    }
}
