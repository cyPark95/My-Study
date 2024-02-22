package pcy.study.api.domain.user.converter;

import pcy.study.api.common.annotation.Converter;
import pcy.study.api.common.api.error.ErrorCode;
import pcy.study.api.common.exception.ApiException;
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
                .orElseThrow(() -> new ApiException("UserRegisterRequest is Null", ErrorCode.NULL_POINT));
    }

    public UserResponse toResponse(User user) {
        return Optional.ofNullable(user)
                .map(it -> UserResponse.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .address(user.getAddress())
                        .build())
                .orElseThrow(() -> new ApiException("User Entity is Null", ErrorCode.NULL_POINT));
    }
}
