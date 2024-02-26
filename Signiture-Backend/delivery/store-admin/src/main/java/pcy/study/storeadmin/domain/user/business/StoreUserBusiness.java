package pcy.study.storeadmin.domain.user.business;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pcy.study.storeadmin.domain.authorization.model.StoreUserDetails;
import pcy.study.storeadmin.domain.store.service.StoreService;
import pcy.study.storeadmin.domain.user.controller.model.StoreUserRegisterRequest;
import pcy.study.storeadmin.domain.user.controller.model.StoreUserResponse;
import pcy.study.storeadmin.domain.user.converter.StoreUserConverter;
import pcy.study.storeadmin.domain.user.service.StoreUserService;

@Service
@RequiredArgsConstructor
public class StoreUserBusiness {

    private final StoreUserService storeUserService;
    private final StoreUserConverter storeUserConverter;
    private final StoreService storeService;
    private final PasswordEncoder passwordEncoder;

    public StoreUserResponse register(StoreUserRegisterRequest request) {
        var store = storeService.getStoreWithThrow(request.storeName());
        var encodedPassword = passwordEncoder.encode(request.password());
        var entity = storeUserConverter.toEntity(request, encodedPassword, store);
        var newEntity = storeUserService.register(entity);
        return storeUserConverter.toResponse(newEntity, store);
    }

    public StoreUserResponse authorization(StoreUserDetails storeUserDetails) {
        return storeUserConverter.toResponse(storeUserDetails);
    }
}
