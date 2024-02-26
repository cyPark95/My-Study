package pcy.study.db.storeuser;

import org.springframework.data.jpa.repository.JpaRepository;
import pcy.study.db.storeuser.enums.StoreUserStatus;

import java.util.Optional;

public interface StoreUserRepository extends JpaRepository<StoreUser, Long> {

    Optional<StoreUser> findFirstByEmailAndStatusOrderByIdDesc(String email, StoreUserStatus status);
}
