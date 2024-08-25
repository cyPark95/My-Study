package pcy.study.storeadmin.utility;

import org.springframework.test.util.ReflectionTestUtils;
import pcy.study.db.userordermenu.UserOrderMenu;

import static pcy.study.storeadmin.utility.StoreUtils.STORE_ID;
import static pcy.study.storeadmin.utility.UserOrderUtils.USER_ORDER_ID;

public class UserOrderMenuUtils {

    public static final Long USER_ORDER_MENU_ID = 1L;

    private UserOrderMenuUtils() {
    }

    public static UserOrderMenu createUserOrderMenu() {
        return UserOrderMenu.builder()
                .storeMenuId(STORE_ID)
                .userOrderId(USER_ORDER_ID)
                .build();
    }

    public static UserOrderMenu createUserOrderMenuWithId() {
        UserOrderMenu userOrderMenu = createUserOrderMenu();
        ReflectionTestUtils.setField(userOrderMenu, "id", USER_ORDER_MENU_ID);
        return userOrderMenu;
    }
}
