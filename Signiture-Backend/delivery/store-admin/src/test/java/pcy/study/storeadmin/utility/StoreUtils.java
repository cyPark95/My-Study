package pcy.study.storeadmin.utility;

import pcy.study.db.store.Store;
import pcy.study.db.store.enums.StoreCategory;

import java.math.BigDecimal;

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
}
