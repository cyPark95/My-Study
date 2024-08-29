package pcy.study.api.domain.userordermenu.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pcy.study.common.api.code.ErrorCode;
import pcy.study.common.exception.ApiException;
import pcy.study.db.userorder.UserOrder;
import pcy.study.db.userordermenu.UserOrderMenu;
import pcy.study.db.userordermenu.UserOrderMenuRepository;
import pcy.study.db.userordermenu.enums.UserOrderMenuStatus;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserOrderMenuService {

    private final UserOrderMenuRepository userOrderMenuRepository;

    public List<UserOrderMenu> searchByUserOrder(UserOrder userOrder) {
        return userOrderMenuRepository.findAllByUserOrderAndStatusOrderByIdDesc(userOrder, UserOrderMenuStatus.REGISTERED);
    }

    public UserOrderMenu order(UserOrderMenu userOrderMenu) {
        return Optional.ofNullable(userOrderMenu)
                .map(userOrderMenuRepository::save)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserOrderMenu Entity is Null"));
    }
}
