package pcy.study.storeadmin.domain.storeuser.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pcy.study.db.storeuser.StoreUser;
import pcy.study.db.storeuser.StoreUserRepository;
import pcy.study.db.storeuser.enums.StoreUserStatus;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreUserService {

    private final StoreUserRepository storeUserRepository;

    public StoreUser register(StoreUser storeUser) {
        return Optional.ofNullable(storeUser)
                .map(storeUserRepository::save)
                .orElseThrow(() -> new IllegalArgumentException("StoreUser Entity is Null"));
    }

    public Optional<StoreUser> getUser(String email) {
        return storeUserRepository.findFirstByEmailAndStatusOrderByIdDesc(email, StoreUserStatus.REGISTERED);
    }
}
