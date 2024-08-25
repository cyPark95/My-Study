package pcy.study.api.utility;

import org.springframework.test.util.ReflectionTestUtils;
import pcy.study.db.userordermenu.UserOrderMenu;

import static pcy.study.api.utility.StoreUtils.STORE_ID;
import static pcy.study.api.utility.UserOrderUtils.USER_ORDER_ID;

public class UserOrderMenuUtils {

    public final static Long USER_ORDER_MENU_ID = 1L;

    private UserOrderMenuUtils() {
    }

    public static UserOrderMenu createUserOrderMenu() {
        return UserOrderMenu.builder()
                .userOrderId(USER_ORDER_ID)
                .storeMenuId(STORE_ID)
                .build();
    }

    public static UserOrderMenu createUserOrderMenuWithId() {
        UserOrderMenu userOrderMenu = createUserOrderMenu();
        ReflectionTestUtils.setField(userOrderMenu, "id", USER_ORDER_MENU_ID);
        return userOrderMenu;
    }
}
