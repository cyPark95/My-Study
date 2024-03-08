package pcy.study.storeadmin.domain.storemenu.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pcy.study.db.storemenu.StoreMenu;
import pcy.study.db.storemenu.StoreMenuRepository;
import pcy.study.storeadmin.config.annotation.ServiceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pcy.study.storeadmin.utility.StoreMenuUtils.STORE_MENU_ID;
import static pcy.study.storeadmin.utility.StoreMenuUtils.createStoreMenu;

@ServiceTest
class StoreMenuServiceTest {

    private StoreMenuService storeMenuService;

    @Autowired
    private StoreMenuRepository storeMenuRepository;

    @BeforeEach
    void setUp() {
        storeMenuService = new StoreMenuService(storeMenuRepository);
    }

    @Test
    @DisplayName("가게 메뉴 조회")
    void getStoreMenuWithThrow() {
        // given
        StoreMenu storeMenu = createStoreMenu();
        storeMenuRepository.save(storeMenu);

        // when
        StoreMenu result = storeMenuService.getStoreMenuWithThrow(STORE_MENU_ID);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("가게 메뉴 조회 - 없는 ID")
    void getStoreMenuWithThrow_failureNotFound() {
        // when
        // then
        assertThatThrownBy(() -> storeMenuService.getStoreMenuWithThrow(-1L))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
