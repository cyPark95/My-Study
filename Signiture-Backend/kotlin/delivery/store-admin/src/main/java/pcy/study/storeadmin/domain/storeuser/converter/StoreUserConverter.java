package pcy.study.storeadmin.domain.storeuser.converter;

import org.springframework.stereotype.Service;
import pcy.study.db.store.Store;
import pcy.study.db.storeuser.StoreUser;
import pcy.study.storeadmin.domain.authorization.model.StoreUserDetails;
import pcy.study.storeadmin.domain.storeuser.controller.model.StoreResponse;
import pcy.study.storeadmin.domain.storeuser.controller.model.StoreUserRegisterRequest;
import pcy.study.storeadmin.domain.storeuser.controller.model.StoreUserResponse;
import pcy.study.storeadmin.domain.storeuser.controller.model.UserResponse;

import java.util.Optional;

@Service
public class StoreUserConverter {

    public StoreUser toEntity(StoreUserRegisterRequest request, String encodedPassword, Store store) {
        return Optional.ofNullable(request)
                .map(it -> StoreUser.builder()
                        .email(request.email())
                        .password(encodedPassword)
                        .role(request.role())
                        .storeId(store.getId())
                        .build())
                .orElseThrow(() -> new IllegalArgumentException("StoreUserRegisterRequest is Null"));
    }

    public StoreUserResponse toResponse(StoreUser storeUser, Store store) {
        return Optional.ofNullable(storeUser)
                .map(it -> StoreUserResponse.builder()
                        .user(getUserResponse(storeUser))
                        .store(getStoreResponse(store))
                        .build())
                .orElseThrow(() -> new IllegalArgumentException("StoreUser Entity is Null"));
    }

    private UserResponse getUserResponse(StoreUser storeUser) {
        return UserResponse.builder()
                .id(storeUser.getId())
                .email(storeUser.getEmail())
                .role(storeUser.getRole())
                .build();
    }

    private StoreResponse getStoreResponse(Store store) {
        return StoreResponse.builder()
                .id(store.getId())
                .name(store.getName())
                .build();
    }

    public StoreUserResponse toResponse(StoreUserDetails storeUserDetails) {
        return Optional.ofNullable(storeUserDetails)
                .map(it -> StoreUserResponse.builder()
                        .user(UserResponse.builder()
                                .id(storeUserDetails.userId())
                                .email(storeUserDetails.email())
                                .role(storeUserDetails.role())
                                .build())
                        .store(StoreResponse.builder()
                                .id(storeUserDetails.storeId())
                                .name(storeUserDetails.storeName())
                                .build())
                        .build())
                .orElseThrow(() -> new IllegalArgumentException("StoreUserDetails is Null"));
    }
}
