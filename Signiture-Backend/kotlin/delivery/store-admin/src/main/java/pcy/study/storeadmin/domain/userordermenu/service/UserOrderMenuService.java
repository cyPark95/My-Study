package pcy.study.storeadmin.domain.userordermenu.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pcy.study.db.userorder.UserOrder;
import pcy.study.db.userordermenu.UserOrderMenu;
import pcy.study.db.userordermenu.UserOrderMenuRepository;
import pcy.study.db.userordermenu.enums.UserOrderMenuStatus;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserOrderMenuService {

    private final UserOrderMenuRepository userOrderMenuRepository;

    public List<UserOrderMenu> getUserOrderMenus(UserOrder userOrder) {
        return userOrderMenuRepository.findAllByUserOrderAndStatusOrderByIdDesc(userOrder, UserOrderMenuStatus.REGISTERED);
    }
}
