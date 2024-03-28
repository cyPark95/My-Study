package pcy.study.sns.common.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pcy.study.sns.common.security.dto.Login;

import java.io.IOException;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper;

    public LoginFilter(ObjectMapper objectMapper) {
        super(new AntPathRequestMatcher("/login", HttpMethod.POST.name()));
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        var login = getLoginInfo(request);
        UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(login.email(), login.password());
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private Login getLoginInfo(HttpServletRequest request) {
        try {
            return objectMapper.readValue(request.getReader(), Login.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("잘못된 로그인 정보입니다.");
        }
    }
}
