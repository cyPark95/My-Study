package pcy.study.storeadmin.utility;

import org.springframework.test.util.ReflectionTestUtils;
import pcy.study.db.store.Store;
import pcy.study.db.userorder.UserOrder;

import java.math.BigDecimal;

import static pcy.study.storeadmin.utility.StoreUtils.STORE_ID;
import static pcy.study.storeadmin.utility.StoreUtils.createStore;

public class UserOrderUtils {

    public static final Long USER_ID = 1L;
    public static final Long USER_ORDER_ID = 1L;

    private UserOrderUtils() {
    }

    public static UserOrder createUserOrder() {
        Store store = createStore();
        return new UserOrder(
                USER_ID,
                store,
                BigDecimal.valueOf(8000.0)
        );
    }

    public static UserOrder createUserOrderWithId() {
        UserOrder userOrder = createUserOrder();
        ReflectionTestUtils.setField(userOrder, "id", USER_ORDER_ID);
        return userOrder;
    }
}
