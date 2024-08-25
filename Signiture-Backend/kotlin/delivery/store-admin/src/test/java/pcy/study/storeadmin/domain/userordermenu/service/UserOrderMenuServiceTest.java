package pcy.study.storeadmin.domain.userordermenu.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pcy.study.db.userordermenu.UserOrderMenu;
import pcy.study.db.userordermenu.UserOrderMenuRepository;
import pcy.study.storeadmin.config.annotation.ServiceTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static pcy.study.storeadmin.utility.UserOrderMenuUtils.createUserOrderMenu;
import static pcy.study.storeadmin.utility.UserOrderUtils.USER_ORDER_ID;

@ServiceTest
class UserOrderMenuServiceTest {

    private UserOrderMenuService userOrderMenuService;

    @Autowired
    private UserOrderMenuRepository userOrderMenuRepository;

    @BeforeEach
    void setUp() {
        userOrderMenuService = new UserOrderMenuService(userOrderMenuRepository);
    }

    @Test
    @DisplayName("사용자 주문 메뉴 조회")
    void getUserOrderMenus() {
        // given
        UserOrderMenu userOrderMenu = createUserOrderMenu();
        userOrderMenuRepository.save(userOrderMenu);

        // when
        List<UserOrderMenu> result = userOrderMenuService.getUserOrderMenus(USER_ORDER_ID);

        // then
        assertThat(result.size()).isEqualTo(1);
    }
}
