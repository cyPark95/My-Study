package pcy.study.db.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pcy.study.db.user.enums.UserStatus;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByIdAndStatusOrderByIdDesc(Long id, UserStatus userStatus);

    Optional<User> findFirstByEmailAndPasswordAndStatusOrderByIdDesc(String email, String password, UserStatus status);
}
