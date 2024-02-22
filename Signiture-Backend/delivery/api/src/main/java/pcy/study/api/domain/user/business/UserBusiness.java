package pcy.study.api.domain.user.business;

import lombok.RequiredArgsConstructor;
import pcy.study.api.common.annotation.Business;
import pcy.study.api.domain.user.controller.model.UserLoginRequest;
import pcy.study.api.domain.user.controller.model.UserRegisterRequest;
import pcy.study.api.domain.user.controller.model.UserResponse;
import pcy.study.api.domain.user.converter.UserConverter;
import pcy.study.api.domain.user.service.UserService;

@Business
@RequiredArgsConstructor
public class UserBusiness {

    private final UserService userService;
    private final UserConverter userConverter;

    public UserResponse register(UserRegisterRequest request) {
        var entity = userConverter.toEntity(request);
        var newEntity = userService.register(entity);
        var response = userConverter.toResponse(newEntity);
        return response;
    }

    public UserResponse login(UserLoginRequest request) {
        var user = userService.login(request.email(), request.password());
        // TODO Token 생성
        var response = userConverter.toResponse(user);
        return response;
    }
}
