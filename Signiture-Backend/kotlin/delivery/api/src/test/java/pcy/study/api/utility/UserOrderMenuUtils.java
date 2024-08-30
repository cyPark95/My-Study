package pcy.study.api.utility;

import org.springframework.test.util.ReflectionTestUtils;
import pcy.study.db.storemenu.StoreMenu;
import pcy.study.db.userorder.UserOrder;
import pcy.study.db.userordermenu.UserOrderMenu;

public class UserOrderMenuUtils {

    public final static Long USER_ORDER_MENU_ID = 1L;

    private UserOrderMenuUtils() {
    }

    public static UserOrderMenu createUserOrderMenu() {
        UserOrder userOrder = UserOrderUtils.createUserOrderWithId();
        StoreMenu storeMenu = StoreMenuUtils.createStoreMenuWithId();
        return new UserOrderMenu(userOrder, storeMenu);
    }

    public static UserOrderMenu createUserOrderMenuWithId() {
        UserOrderMenu userOrderMenu = createUserOrderMenu();
        ReflectionTestUtils.setField(userOrderMenu, "id", USER_ORDER_MENU_ID);
        return userOrderMenu;
    }
}
