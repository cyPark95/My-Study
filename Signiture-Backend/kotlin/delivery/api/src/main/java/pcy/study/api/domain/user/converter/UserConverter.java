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
                .map(it -> User.builder()
                        .name(request.name())
                        .email(request.email())
                        .password(request.password())
                        .address(request.address())
                        .build())
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserRegisterRequest is Null"));
    }

    public UserResponse toResponse(User user) {
        return Optional.ofNullable(user)
                .map(it -> UserResponse.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .address(user.getAddress())
                        .build())
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "User Entity is Null"));    }
}
