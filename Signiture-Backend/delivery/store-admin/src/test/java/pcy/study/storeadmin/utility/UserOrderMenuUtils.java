package pcy.study.storeadmin.utility;

import pcy.study.db.userordermenu.UserOrderMenu;

import static pcy.study.storeadmin.utility.StoreUtils.STORE_ID;
import static pcy.study.storeadmin.utility.UserOrderUtils.USER_ORDER_ID;

public class UserOrderMenuUtils {

    private UserOrderMenuUtils() {
    }

    public static UserOrderMenu createUserOrderMenu() {
        return UserOrderMenu.builder()
                .storeMenuId(STORE_ID)
                .userOrderId(USER_ORDER_ID)
                .build();
    }
}
