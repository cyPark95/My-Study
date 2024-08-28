package pcy.study.api.domain.user.business;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import pcy.study.common.annotation.Business;
import pcy.study.api.domain.token.business.TokenBusiness;
import pcy.study.api.domain.token.controller.model.TokenResponse;
import pcy.study.api.domain.user.controller.model.UserLoginRequest;
import pcy.study.api.domain.user.controller.model.UserRegisterRequest;
import pcy.study.api.domain.user.controller.model.UserResponse;
import pcy.study.api.domain.user.converter.UserConverter;
import pcy.study.api.domain.user.service.UserService;

@Business
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserBusiness {

    private final UserService userService;
    private final UserConverter userConverter;
    private final TokenBusiness tokenBusiness;

    @Transactional
    public UserResponse register(UserRegisterRequest request) {
        var entity = userConverter.toEntity(request);
        var newEntity = userService.register(entity);
        return userConverter.toResponse(newEntity);
    }

    public TokenResponse login(UserLoginRequest request) {
        var user = userService.login(request.email(), request.password());
        var userId = user.getId();
        return tokenBusiness.issueToken(userId);
    }

    public UserResponse info(Long id) {
        var user = userService.getUserWithThrow(id);
        return userConverter.toResponse(user);
    }
}
