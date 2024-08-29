package pcy.study.api.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pcy.study.common.api.code.ErrorCode;
import pcy.study.common.api.code.UserErrorCode;
import pcy.study.common.exception.ApiException;
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
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "User Entity is Null"));
    }

    public User login(String email, String password) {
        return getUserWithThrow(email, password);
    }

    private User getUserWithThrow(String email, String password) {
        return Optional.ofNullable(userRepository.findFirstByEmailAndPasswordAndStatusOrderByIdDesc(
                        email,
                        password,
                        UserStatus.REGISTERED
                ))
                .orElseThrow(() -> new ApiException(
                        UserErrorCode.USER_NOT_FOUND,
                        String.format("Email: [%s], Password: [%s] User Not Found", email, password)
                ));
    }

    public User getUserWithThrow(Long id) {
        return Optional.ofNullable(userRepository.findFirstByIdAndStatusOrderByIdDesc(id, UserStatus.REGISTERED))
                .orElseThrow(() -> new ApiException(
                        UserErrorCode.USER_NOT_FOUND,
                        String.format("ID: [%d] User Not Found", id)
                ));
    }
}
