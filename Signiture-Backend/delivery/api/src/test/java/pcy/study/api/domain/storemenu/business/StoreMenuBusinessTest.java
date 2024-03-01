package pcy.study.api.domain.storemenu.business;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import pcy.study.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import pcy.study.api.domain.storemenu.controller.model.StoreMenuResponse;
import pcy.study.api.domain.storemenu.converter.StoreMenuConverter;
import pcy.study.api.domain.storemenu.service.StoreMenuService;
import pcy.study.api.utility.StoreMenuUtils;
import pcy.study.db.storemenu.StoreMenu;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static pcy.study.api.utility.StoreMenuUtils.*;
import static pcy.study.api.utility.StoreUtils.STORE_ID;

@ExtendWith(MockitoExtension.class)
class StoreMenuBusinessTest {

    @InjectMocks
    private StoreMenuBusiness storeMenuBusiness;

    @Spy
    private StoreMenuConverter storeMenuConverter;
    @Mock
    private StoreMenuService storeMenuService;

    @Test
    @DisplayName("가게 메뉴 등록")
    void register() {
        // given
        StoreMenuRegisterRequest request = createStoreMenuRegisterRequest();

        StoreMenu storeMenu = createStoreMenuWithId();
        when(storeMenuService.register(any(StoreMenu.class))).thenReturn(storeMenu);

        // when
        StoreMenuResponse result = storeMenuBusiness.register(request);

        // then
        assertEqualsStoreMenuResponse(result);
    }

    @Test
    @DisplayName("가게에 등록된 메뉴 조회")
    void searchByStore() {
        // given
        StoreMenu storeMenu = createStoreMenuWithId();
        when(storeMenuService.searchByStoreId(STORE_ID)).thenReturn(List.of(storeMenu));

        // when
        List<StoreMenuResponse> results = storeMenuBusiness.searchByStore(STORE_ID);

        // then
        assertThat(results.size()).isEqualTo(1);
        results.forEach(StoreMenuUtils::assertEqualsStoreMenuResponse);
    }

    private StoreMenu createStoreMenuWithId() {
        StoreMenu storeMenu = createStoreMenu();
        ReflectionTestUtils.setField(storeMenu, "id", STORE_MENU_ID);
        return storeMenu;
    }
}
