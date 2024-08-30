package pcy.study.storeadmin.utility;

import org.springframework.test.util.ReflectionTestUtils;
import pcy.study.db.storemenu.StoreMenu;
import pcy.study.db.userorder.UserOrder;
import pcy.study.db.userordermenu.UserOrderMenu;

import static pcy.study.storeadmin.utility.StoreMenuUtils.createStoreMenu;
import static pcy.study.storeadmin.utility.UserOrderUtils.createUserOrder;

public class UserOrderMenuUtils {

    public static final Long USER_ORDER_MENU_ID = 1L;

    private UserOrderMenuUtils() {
    }

    public static UserOrderMenu createUserOrderMenu() {
        StoreMenu storeMenu = createStoreMenu();
        UserOrder userOrder = createUserOrder();
        return new UserOrderMenu(
                userOrder,
                storeMenu
        );
    }

    public static UserOrderMenu createUserOrderMenuWithId() {
        UserOrderMenu userOrderMenu = createUserOrderMenu();
        ReflectionTestUtils.setField(userOrderMenu, "id", USER_ORDER_MENU_ID);
        return userOrderMenu;
    }
}
