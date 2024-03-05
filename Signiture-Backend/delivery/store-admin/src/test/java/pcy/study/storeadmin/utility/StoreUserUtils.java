package pcy.study.storeadmin.utility;

import org.springframework.test.util.ReflectionTestUtils;
import pcy.study.db.storeuser.StoreUser;
import pcy.study.db.storeuser.enums.StoreUserRole;
import pcy.study.storeadmin.domain.storeuser.controller.model.StoreResponse;
import pcy.study.storeadmin.domain.storeuser.controller.model.StoreUserRegisterRequest;
import pcy.study.storeadmin.domain.storeuser.controller.model.StoreUserResponse;
import pcy.study.storeadmin.domain.storeuser.controller.model.UserResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static pcy.study.storeadmin.utility.StoreUtils.STORE_ID;
import static pcy.study.storeadmin.utility.StoreUtils.STORE_NAME;

public class StoreUserUtils {

    public static final Long STORE_USER_ID = 1L;
    public static final String STORE_USER_EMAIL = "email@gmail.com";
    public static final String STORE_USER_PASSWORD = "password";
    public static final String STORE_USER_ENCODED_PASSWORD = "encode_password";

    private StoreUserUtils() {
    }

    public static StoreUser createStoreUser() {
        return StoreUser.builder()
                .storeId(STORE_ID)
                .email(STORE_USER_EMAIL)
                .password(STORE_USER_PASSWORD)
                .role(StoreUserRole.USER)
                .build();
    }

    public static StoreUserRegisterRequest createStoreUserRegisterRequest() {
        return new StoreUserRegisterRequest(
                STORE_NAME,
                STORE_USER_EMAIL,
                STORE_USER_PASSWORD,
                StoreUserRole.USER
        );
    }

    public static StoreUser createStoreUserWithId() {
        StoreUser storeUser = createStoreUser();
        ReflectionTestUtils.setField(storeUser, "id", STORE_USER_ID);
        return storeUser;
    }

    public static void assertEqualsStoreUserResponse(StoreUserResponse result) {
        assertAll(
                () -> assertEqualsUserResponse(result.user()),
                () -> assertEqualsStoreResponse(result.store())
        );
    }

    private static void assertEqualsUserResponse(UserResponse user) {
        assertAll(
                () -> assertThat(user.id()).isEqualTo(STORE_USER_ID),
                () -> assertThat(user.email()).isEqualTo(STORE_USER_EMAIL),
                () -> assertThat(user.role()).isEqualTo(StoreUserRole.USER)
        );
    }

    private static void assertEqualsStoreResponse(StoreResponse store) {
        assertAll(
                () -> assertThat(store.id()).isEqualTo(STORE_ID),
                () -> assertThat(store.name()).isEqualTo(STORE_NAME)
        );
    }
}
