package pcy.study.api.domain.store.business;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import pcy.study.api.domain.store.controller.model.StoreRegisterRequest;
import pcy.study.api.domain.store.controller.model.StoreResponse;
import pcy.study.api.domain.store.converter.StoreConverter;
import pcy.study.api.domain.store.service.StoreService;
import pcy.study.api.utility.StoreUtils;
import pcy.study.db.store.Store;
import pcy.study.db.store.enums.StoreCategory;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static pcy.study.api.utility.StoreUtils.*;

@ExtendWith(MockitoExtension.class)
class StoreBusinessTest {

    @InjectMocks
    private StoreBusiness storeBusiness;

    @Spy
    private StoreConverter storeConverter;
    @Mock
    private StoreService storeService;

    @Test
    @DisplayName("가게 등록")
    void register() {
        // given
        StoreRegisterRequest request = createStoreRegisterRequest();

        Store store = createStoreWithId();
        when(storeService.register(any(Store.class))).thenReturn(store);

        // when
        StoreResponse result = storeBusiness.register(request);

        // then
        assertEqualsStoreResponse(result);
    }

    @Test
    @DisplayName("카테고리 별 가게 조회")
    void searchByCategory() {
        // given
        Store store = createStoreWithId();
        when(storeService.searchByCategory(StoreCategory.COFFEE_TEA)).thenReturn(List.of(store));

        // when
        List<StoreResponse> results = storeBusiness.searchByCategory(StoreCategory.COFFEE_TEA);

        // then
        results.forEach(StoreUtils::assertEqualsStoreResponse);
    }

    private Store createStoreWithId() {
        Store store = createStore();
        ReflectionTestUtils.setField(store, "id", STORE_ID);
        return store;
    }
}
