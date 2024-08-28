package pcy.study.api.domain.userordermenu.convertor;

import pcy.study.common.annotation.Converter;
import pcy.study.db.storemenu.StoreMenu;
import pcy.study.db.userorder.UserOrder;
import pcy.study.db.userordermenu.UserOrderMenu;

@Converter
public class UserOrderMenuConverter {

    public UserOrderMenu toEntity(UserOrder userOrder, StoreMenu storeMenu) {
        return UserOrderMenu.builder()
                .userOrderId(userOrder.getId())
                .storeMenuId(storeMenu.getId())
                .build();
    }
}
