package pcy.study.storeadmin.domain.store.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pcy.study.db.store.Store;
import pcy.study.db.store.StoreRepository;
import pcy.study.storeadmin.config.annotation.ServiceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pcy.study.storeadmin.utility.StoreUtils.*;

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
    @DisplayName("ID로 가게 조회")
    void getStoreWithThrowById() {
        // when
        Store result = storeService.getStoreWithThrow(store.getId());

        // then
        assertThat(result).isEqualTo(store);
    }

    @Test
    @DisplayName("ID로 가게 조회 - 없는 ID")
    void getStoreWithThrowById_failureNotFound() {
        // when
        // then
        assertThatThrownBy(() -> storeService.getStoreWithThrow(-1L))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이름으로 가게 조회")
    void getStoreWithThrowByName() {
        // when
        Store result = storeService.getStoreWithThrow(store.getName());

        // then
        assertThat(result).isEqualTo(store);
    }

    @Test
    @DisplayName("이름으로 가게 조회 - 없는 이름")
    void getStoreWithThrowByName_failureNotFound() {
        // when
        // then
        assertThatThrownBy(() -> storeService.getStoreWithThrow("example"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private Store saveStore() {
        Store store = createStore();
        storeRepository.save(store);
        return store;
    }
}
