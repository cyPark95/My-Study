package pcy.study.api.resolver;

import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import pcy.study.api.domain.user.model.UserDetails;
import pcy.study.api.domain.user.service.UserService;
import pcy.study.common.annotation.UserSession;
import pcy.study.common.api.code.ErrorCode;
import pcy.study.common.exception.ApiException;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserSessionResolver implements HandlerMethodArgumentResolver {

    private final UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        var isAnnotation = parameter.hasParameterAnnotation(UserSession.class);
        var isParameterType = parameter.getParameterType().equals(UserDetails.class);
        return isAnnotation && isParameterType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        var requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
        var attribute = requestContext.getAttribute("userId", RequestAttributes.SCOPE_REQUEST);
        var userId = Optional.ofNullable(attribute)
                .map(it -> Long.parseLong(it.toString()))
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "User ID Attribute is Null in Request Context"));

        var user = userService.getUserWithThrow(userId);
        return UserDetails.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .address(user.getAddress())
                .build();
    }
}
