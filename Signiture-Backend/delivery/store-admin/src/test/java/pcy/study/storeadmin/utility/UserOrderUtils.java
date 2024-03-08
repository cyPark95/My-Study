package pcy.study.storeadmin.utility;

import org.springframework.test.util.ReflectionTestUtils;
import pcy.study.db.userorder.UserOrder;

import java.math.BigDecimal;

import static pcy.study.storeadmin.utility.StoreUtils.STORE_ID;

public class UserOrderUtils {

    public static final Long USER_ID = 1L;
    public static final Long USER_ORDER_ID = 1L;

    private UserOrderUtils() {
    }

    public static UserOrder createUserOrder() {
        return UserOrder.builder()
                .userId(USER_ID)
                .storeId(STORE_ID)
                .amount(BigDecimal.valueOf(8000.0))
                .build();
    }

    public static UserOrder createUserOrderWithId() {
        UserOrder userOrder = createUserOrder();
        ReflectionTestUtils.setField(userOrder, "id", USER_ORDER_ID);
        return userOrder;
    }
}
