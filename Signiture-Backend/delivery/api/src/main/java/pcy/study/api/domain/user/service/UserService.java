package pcy.study.api.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pcy.study.api.common.api.error.ErrorCode;
import pcy.study.api.common.api.error.UserErrorCode;
import pcy.study.api.common.exception.ApiException;
import pcy.study.db.user.User;
import pcy.study.db.user.UserRepository;
import pcy.study.db.user.enums.UserStatus;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User register(User user) {
        return Optional.ofNullable(user)
                .map(userRepository::save)
                .orElseThrow(() -> new ApiException("User Entity is Null", ErrorCode.NULL_POINT));
    }

    public User login(String email, String password) {
        var entity = getUserWithThrow(email, password);
        return entity;
    }

    public User getUserWithThrow(String email, String password) {
        return userRepository.findFirstByEmailAndPasswordAndStatusOrderByIdDesc(
                        email,
                        password,
                        UserStatus.REGISTERED
                )
                .orElseThrow(() -> new ApiException(
                        String.format("Email: [%s], Password: [%s] User Not Found", email, password),
                        UserErrorCode.USER_NOT_FOUND)
                );
    }
}
