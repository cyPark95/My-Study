package pcy.study.api.utility;

import pcy.study.db.userordermenu.UserOrderMenu;

import static pcy.study.api.utility.StoreUtils.STORE_ID;
import static pcy.study.api.utility.UserOrderUtils.USER_ORDER_ID;

public class UserOrderMenuUtils {

    private UserOrderMenuUtils() {
    }

    public static UserOrderMenu createUserOrderMenu() {
        return UserOrderMenu.builder()
                .userOrderId(USER_ORDER_ID)
                .storeMenuId(STORE_ID)
                .build();
    }
}
