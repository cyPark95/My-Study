package pcy.study.api.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pcy.study.api.common.api.error.ErrorCode;
import pcy.study.api.common.exception.ApiException;
import pcy.study.db.user.User;
import pcy.study.db.user.UserRepository;

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
}
