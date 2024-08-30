package pcy.study.api.domain.store.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pcy.study.common.exception.ApiException;
import pcy.study.api.config.annotation.ServiceTest;
import pcy.study.db.store.Store;
import pcy.study.db.store.StoreRepository;
import pcy.study.db.store.enums.StoreCategory;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pcy.study.api.utility.StoreUtils.createStore;

@ServiceTest
class StoreServiceTest {

    private StoreService storeService;

    @Autowired
    private StoreRepository storeRepository;

    private Store store;

    @BeforeEach
    void setUp() {
        storeService = new StoreService(storeRepository);

        // given
        store = saveStore();
    }

    @Test
    @DisplayName("가게 등록")
    void register() {
        // given
        Store store = new Store(
                "name",
                "서울특별시",
                StoreCategory.KOREAN_FOOD,
                "https://www.example.com",
                BigDecimal.valueOf(10000),
                BigDecimal.valueOf(7000),
                "010-0000-0000"
        );

        // when
        Store result = storeService.register(store);

        // then
        assertThat(result.getId()).isNotNull();
    }

    @Test
    @DisplayName("가게 등록 - Null 파라미터")
    void register_failureNullParameter() {
        // when
        // then
        assertThatThrownBy(() -> storeService.register(null))
                .isInstanceOf(ApiException.class);
    }

    @Test
    @DisplayName("가게 조회")
    void getStoreWithThrow() {
        // when
        Store result = storeService.getStoreWithThrow(store.getId());

        // then
        assertThat(result).isEqualTo(store);
    }

    @Test
    @DisplayName("가게 조회 - 없는 ID")
    void getStoreWithThrow_failureNotFound() {
        // when
        // then
        assertThatThrownBy(() -> storeService.getStoreWithThrow(-1L))
                .isInstanceOf(ApiException.class);
    }

    @Test
    @DisplayName("카테고리 별 가게 조회")
    void searchByCategory() {
        // when
        List<Store> result = storeService.searchByCategory(StoreCategory.COFFEE_TEA);

        // then
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("모든 가게 조회")
    void getAll() {
        // when
        List<Store> result = storeService.getAll();

        // then
        assertThat(result.size()).isEqualTo(1);
    }

    private Store saveStore() {
        Store store = createStore();
        return storeRepository.save(store);
    }
}
