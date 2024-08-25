package pcy.study.api.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import pcy.study.api.common.api.error.ErrorCode;
import pcy.study.api.common.api.error.TokenErrorCode;
import pcy.study.api.common.exception.ApiException;
import pcy.study.api.domain.token.business.TokenBusiness;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final TokenBusiness tokenBusiness;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("Authorization Interceptor URL = {}", request.getRequestURI());

        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return true;
        }

        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }

        var accessToken = request.getHeader("authorization-token");
        Objects.requireNonNull(accessToken, () -> {
            throw new ApiException(TokenErrorCode.AUTHORIZATION_TOKEN_NOT_FOUND);
        });

        var userId = tokenBusiness.validationAccessToken(accessToken);
        if (Objects.nonNull(userId)) {
            var requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
            requestContext.setAttribute("userId", userId, RequestAttributes.SCOPE_REQUEST);
            return true;
        }

        throw new ApiException(String.format("AccessToken: [%s] Invalid Token", accessToken), ErrorCode.ACCESS_DENIED);
    }
}
