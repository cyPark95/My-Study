package pcy.study.simpleboard.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Slf4j
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    public static final String AUTHENTICATION_KEY = "Authentication";
    public static final String AUTHENTICATION_VALUE = "TRUE";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 메서드 시작 전 호출
        log.info(">>> Interceptor PreHandle");

        var handlerMethod = (HandlerMethod) handler;

        var methodLevel = handlerMethod.getMethodAnnotation(Authenticated.class);
        var classLevel = handlerMethod.getBeanType().getAnnotation(Authenticated.class);

        if (Objects.nonNull(methodLevel) || Objects.nonNull(classLevel)) {
            if (!Objects.equals(AUTHENTICATION_VALUE, request.getHeader(AUTHENTICATION_KEY))) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        // 뷰 렌더링 전 호출
        log.info(">>> Interceptor PostHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 뷰 렌더링 후 호출
        // 예외 발생 여부에 관계 없이 항상 호출
        log.info(">>> Interceptor AfterCompletion");
    }
}
