package pcy.study.api.utility;

import io.restassured.path.json.JsonPath;
import org.springframework.test.util.ReflectionTestUtils;
import pcy.study.api.domain.store.controller.model.StoreRegisterRequest;
import pcy.study.api.domain.store.controller.model.StoreResponse;
import pcy.study.db.store.Store;
import pcy.study.db.store.enums.StoreCategory;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class StoreUtils {

    public static final Long STORE_ID = 1L;
    public static final String STORE_NAME = "store name";
    public static final String STORE_ADDRESS = "서울특별시 관악구 xxx";
    public static final String STORE_THUMBNAIL_URL = "https://www.thumbnail.xxx";
    public static final BigDecimal STORE_MINIMUM_AMOUNT = BigDecimal.valueOf(8000.0);
    public static final BigDecimal STORE_MINIMUM_DELIVERY_AMOUNT = BigDecimal.valueOf(11000.0);
    public static final String STORE_PHONE_NUMBER = "010-0000-0000";
    public static final Double STORE_STAR = 0d;

    private StoreUtils() {
    }

    public static Store createStore() {
        return Store.builder()
                .name(STORE_NAME)
                .address(STORE_ADDRESS)
                .category(StoreCategory.COFFEE_TEA)
                .thumbnailUrl(STORE_THUMBNAIL_URL)
                .minimumAmount(STORE_MINIMUM_AMOUNT)
                .minimumDeliveryAmount(STORE_MINIMUM_DELIVERY_AMOUNT)
                .phoneNumber(STORE_PHONE_NUMBER)
                .build();
    }

    public static Store createStoreWithId() {
        Store store = createStore();
        ReflectionTestUtils.setField(store, "id", STORE_ID);
        return store;
    }

    public static StoreRegisterRequest createStoreRegisterRequest() {
        return new StoreRegisterRequest(
                STORE_NAME,
                STORE_ADDRESS,
                StoreCategory.COFFEE_TEA,
                STORE_THUMBNAIL_URL,
                STORE_MINIMUM_AMOUNT,
                STORE_MINIMUM_DELIVERY_AMOUNT,
                STORE_PHONE_NUMBER
        );
    }

    public static void assertEqualsStoreResponse(StoreResponse response) {
        assertAll(
                () -> assertThat(response.id()).isEqualTo(STORE_ID),
                () -> assertThat(response.name()).isEqualTo(STORE_NAME),
                () -> assertThat(response.address()).isEqualTo(STORE_ADDRESS),
                () -> assertThat(response.category()).isEqualTo(StoreCategory.COFFEE_TEA),
                () -> assertThat(response.star()).isEqualTo(STORE_STAR),
                () -> assertThat(response.thumbnailUrl()).isEqualTo(STORE_THUMBNAIL_URL),
                () -> assertThat(response.minimumAmount()).isEqualTo(STORE_MINIMUM_AMOUNT),
                () -> assertThat(response.minimumDeliveryAmount()).isEqualTo(STORE_MINIMUM_DELIVERY_AMOUNT),
                () -> assertThat(response.phoneNumber()).isEqualTo(STORE_PHONE_NUMBER)
        );
    }

    public static void assertEqualsStoreResponse(JsonPath jsonPath) {
        assertAll(
                () -> assertThat(jsonPath.getLong("body.id")).isEqualTo(STORE_ID),
                () -> assertThat(jsonPath.getString("body.name")).isEqualTo(STORE_NAME),
                () -> assertThat(jsonPath.getString("body.address")).isEqualTo(STORE_ADDRESS),
                () -> assertThat(jsonPath.getString("body.category")).isEqualTo(StoreCategory.COFFEE_TEA.name()),
                () -> assertThat(jsonPath.getDouble("body.star")).isEqualTo(STORE_STAR),
                () -> assertThat(jsonPath.getString("body.thumbnail_url")).isEqualTo(STORE_THUMBNAIL_URL),
                () -> assertThat(jsonPath.getObject("body.minimum_amount", BigDecimal.class)).isEqualTo(STORE_MINIMUM_AMOUNT),
                () -> assertThat(jsonPath.getObject("body.minimum_delivery_amount", BigDecimal.class)).isEqualTo(STORE_MINIMUM_DELIVERY_AMOUNT),
                () -> assertThat(jsonPath.getString("body.phone_number")).isEqualTo(STORE_PHONE_NUMBER)
        );
    }
}
