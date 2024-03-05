package pcy.study.storeadmin.domain.storeuser.business;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import pcy.study.db.store.Store;
import pcy.study.db.storeuser.StoreUser;
import pcy.study.db.storeuser.enums.StoreUserRole;
import pcy.study.db.storeuser.enums.StoreUserStatus;
import pcy.study.storeadmin.domain.authorization.model.StoreUserDetails;
import pcy.study.storeadmin.domain.store.service.StoreService;
import pcy.study.storeadmin.domain.storeuser.controller.model.StoreUserRegisterRequest;
import pcy.study.storeadmin.domain.storeuser.controller.model.StoreUserResponse;
import pcy.study.storeadmin.domain.storeuser.converter.StoreUserConverter;
import pcy.study.storeadmin.domain.storeuser.service.StoreUserService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static pcy.study.storeadmin.utility.StoreUserUtils.*;
import static pcy.study.storeadmin.utility.StoreUtils.*;

@ExtendWith(MockitoExtension.class)
class StoreUserBusinessTest {

    @InjectMocks
    private StoreUserBusiness storeUserBusiness;

    @Mock
    private StoreUserService storeUserService;
    @Spy
    private StoreUserConverter storeUserConverter;
    @Mock
    private StoreService storeService;
    @Spy
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("가맹점 사용자 등록")
    void register() {
        // given
        StoreUserRegisterRequest request = createStoreUserRegisterRequest();

        Store store = createStoreWithId();
        when(storeService.getStoreWithThrow(request.storeName())).thenReturn(store);
        StoreUser storeUser = createStoreUserWithId();
        when(storeUserService.register(any(StoreUser.class))).thenReturn(storeUser);

        // when
        StoreUserResponse result = storeUserBusiness.register(request);

        // then
        assertEqualsStoreUserResponse(result);
    }

    @Test
    @DisplayName("가맹점 사용자 정보 조회")
    void authorization() {
        // given
        StoreUserDetails userDetails = StoreUserDetails.builder()
                .userId(STORE_USER_ID)
                .email(STORE_USER_EMAIL)
                .password(STORE_USER_ENCODED_PASSWORD)
                .role(StoreUserRole.USER)
                .status(StoreUserStatus.REGISTERED)
                .storeId(STORE_ID)
                .storeName(STORE_NAME)
                .build();

        // when
        StoreUserResponse result = storeUserBusiness.authorization(userDetails);

        // then
        assertEqualsStoreUserResponse(result);
    }
}
