package pcy.study.storeadmin.domain.storeuser.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pcy.study.db.storeuser.StoreUser;
import pcy.study.db.storeuser.StoreUserRepository;
import pcy.study.db.storeuser.enums.StoreUserRole;
import pcy.study.storeadmin.config.annotation.ServiceTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pcy.study.storeadmin.utility.StoreUserUtils.STORE_USER_EMAIL;
import static pcy.study.storeadmin.utility.StoreUserUtils.createStoreUser;
import static pcy.study.storeadmin.utility.StoreUtils.STORE_ID;

@ServiceTest
class StoreUserServiceTest {

    private StoreUserService storeUserService;

    @Autowired
    private StoreUserRepository storeUserRepository;

    @BeforeEach
    void setUp() {
        storeUserService = new StoreUserService(storeUserRepository);
    }

    @Test
    @DisplayName("가맹점 사용자 등록")
    void register() {
        // given
        StoreUser storeUser = StoreUser.builder()
                .storeId(STORE_ID)
                .email("example@gmail.com")
                .password("example")
                .role(StoreUserRole.USER)
                .build();

        // when
        StoreUser result = storeUserService.register(storeUser);

        // then
        assertThat(result.getId()).isNotNull();
    }

    @Test
    @DisplayName("가맹점 사용자 등록 - Null 파라미터")
    void register_failureNullParameter() {
        // when
        // then
        assertThatThrownBy(() -> storeUserService.register(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("가맹점 사용자 조회")
    void getUser() {
        // given
        StoreUser storeUser = saveStoreUser();

        // when
        Optional<StoreUser> result = storeUserService.getUser(STORE_USER_EMAIL);

        // then
        assertThat(result.isPresent()).isTrue();
    }

    private StoreUser saveStoreUser() {
        StoreUser storeUser = createStoreUser();
        storeUserRepository.save(storeUser);
        return storeUser;
    }
}
