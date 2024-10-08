package pcy.study.api.domain.user.converter;

import pcy.study.common.annotation.Converter;
import pcy.study.common.api.code.ErrorCode;
import pcy.study.common.exception.ApiException;
import pcy.study.api.domain.user.controller.model.UserRegisterRequest;
import pcy.study.api.domain.user.controller.model.UserResponse;
import pcy.study.db.user.User;

import java.util.Optional;

@Converter
public class UserConverter {

    public User toEntity(UserRegisterRequest request) {
        return Optional.ofNullable(request)
                .map(it -> new User(
                        request.getName(),
                        request.getEmail(),
                        request.getPassword(),
                        request.getAddress()
                ))
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserRegisterRequest is Null"));
    }

    public UserResponse toResponse(User user) {
        return Optional.ofNullable(user)
                .map(it -> new UserResponse(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getAddress()
                ))
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "User Entity is Null"));
    }
}
