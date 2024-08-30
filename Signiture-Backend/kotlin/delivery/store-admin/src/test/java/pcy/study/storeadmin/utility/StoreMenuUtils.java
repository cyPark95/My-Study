package pcy.study.storeadmin.utility;

import org.springframework.test.util.ReflectionTestUtils;
import pcy.study.db.store.Store;
import pcy.study.db.storemenu.StoreMenu;

import java.math.BigDecimal;

import static pcy.study.storeadmin.utility.StoreUtils.createStore;

public class StoreMenuUtils {

    public static final Long STORE_MENU_ID = 1L;
    public static final String STORE_MENU_NAME = "store menu name";
    public static final BigDecimal STORE_MENU_AMOUNT = BigDecimal.valueOf(8000.0);
    public static final String STORE_MENU_THUMBNAIL = "https://www.thumbnail.xxx";

    private StoreMenuUtils() {
    }

    public static StoreMenu createStoreMenu() {
        Store store = createStore();
        return new StoreMenu(
                store,
                STORE_MENU_NAME,
                STORE_MENU_AMOUNT,
                STORE_MENU_THUMBNAIL
        );
    }

    public static StoreMenu createStoreMenuWithId() {
        StoreMenu storeMenu = createStoreMenu();
        ReflectionTestUtils.setField(storeMenu, "id", STORE_MENU_ID);
        return storeMenu;
    }
}
