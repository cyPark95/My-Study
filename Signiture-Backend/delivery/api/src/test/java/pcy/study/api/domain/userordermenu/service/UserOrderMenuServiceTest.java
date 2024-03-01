package pcy.study.api.domain.userordermenu.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pcy.study.api.common.exception.ApiException;
import pcy.study.api.config.annotation.ServiceTest;
import pcy.study.db.userordermenu.UserOrderMenu;
import pcy.study.db.userordermenu.UserOrderMenuRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pcy.study.api.utility.UserOrderMenuUtils.createUserOrderMenu;

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
    void searchByUserOrder() {
        // given
        UserOrderMenu userOrderMenu = createUserOrderMenu();
        userOrderMenuRepository.save(userOrderMenu);

        // when
        List<UserOrderMenu> result = userOrderMenuService.searchByUserOrder(userOrderMenu.getId());

        // then
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("사용자 메뉴 주문")
    void order() {
        // given
        UserOrderMenu userOrderMenu = createUserOrderMenu();

        // when
        UserOrderMenu result = userOrderMenuService.order(userOrderMenu);

        // then
        assertThat(result.getId()).isNotNull();
    }

    @Test
    @DisplayName("사용자 메뉴 주문 - Null 파라미터")
    void order_failureNullParameter() {
        // when
        // then
        assertThatThrownBy(() -> userOrderMenuService.order(null))
                .isInstanceOf(ApiException.class);
    }
}
