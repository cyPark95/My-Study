package pcy.study.api.domain.storemenu.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pcy.study.api.config.annotation.ServiceTest;
import pcy.study.api.utility.StoreUtils;
import pcy.study.common.exception.ApiException;
import pcy.study.db.store.Store;
import pcy.study.db.storemenu.StoreMenu;
import pcy.study.db.storemenu.StoreMenuRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pcy.study.api.utility.StoreMenuUtils.createStoreMenu;
import static pcy.study.api.utility.StoreUtils.STORE_ID;

@ServiceTest
class StoreMenuServiceTest {

    private StoreMenuService storeMenuService;

    @Autowired
    private StoreMenuRepository storeMenuRepository;

    private StoreMenu storeMenu;

    @BeforeEach
    void setUp() {
        storeMenuService = new StoreMenuService(storeMenuRepository);

        // given
        storeMenu = saveStoreMenu();
    }

    @Test
    @DisplayName("가게 메뉴 등록")
    void register() {
        // given
        Store store = StoreUtils.createStoreWithId();
        StoreMenu storeMenu = new StoreMenu(
                store,
                "name",
                BigDecimal.valueOf(10000),
                "https://www.example.com"
        );

        // when
        StoreMenu result = storeMenuService.register(storeMenu);

        // then
        assertThat(result.getId()).isNotNull();
    }

    @Test
    @DisplayName("가게 메뉴 등록 - Null 파라미터")
    void register_failureNullParameter() {
        // when
        // then
        assertThatThrownBy(() -> storeMenuService.register(null))
                .isInstanceOf(ApiException.class);
    }

    @Test
    @DisplayName("가게 메뉴 조회")
    void getStoreMenuWithThrow() {
        // when
        StoreMenu result = storeMenuService.getStoreMenuWithThrow(storeMenu.getId());

        // then
        assertThat(result).isEqualTo(storeMenu);
    }

    @Test
    @DisplayName("가게 메뉴 조회 - 없는 ID")
    void getStoreWithThrow_failureNotFound() {
        // when
        // then
        assertThatThrownBy(() -> storeMenuService.getStoreMenuWithThrow(-1L))
                .isInstanceOf(ApiException.class);
    }

    @Test
    @DisplayName("가게에 등록된 메뉴 조회")
    void searchByStoreId() {
        // when
        List<StoreMenu> result = storeMenuService.searchByStoreId(STORE_ID);

        // then
        assertThat(result.size()).isEqualTo(1);
    }

    private StoreMenu saveStoreMenu() {
        StoreMenu storeMenu = createStoreMenu();
        return storeMenuRepository.save(storeMenu);
    }
}
