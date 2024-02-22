package pcy.study.api.domain.user.business;

import lombok.RequiredArgsConstructor;
import pcy.study.api.common.annotation.Business;
import pcy.study.api.domain.token.business.TokenBusiness;
import pcy.study.api.domain.token.controller.model.TokenResponse;
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

    private final TokenBusiness tokenBusiness;

    public UserResponse register(UserRegisterRequest request) {
        var entity = userConverter.toEntity(request);
        var newEntity = userService.register(entity);
        var response = userConverter.toResponse(newEntity);
        return response;
    }

    public TokenResponse login(UserLoginRequest request) {
        var user = userService.login(request.email(), request.password());
        var userId = user.getId();
        var tokenResponse = tokenBusiness.issueToken(userId);
        return tokenResponse;
    }

    public UserResponse info(Long id) {
        var user = userService.getUserWithThrow(id);
        var response = userConverter.toResponse(user);
        return response;
    }
}
