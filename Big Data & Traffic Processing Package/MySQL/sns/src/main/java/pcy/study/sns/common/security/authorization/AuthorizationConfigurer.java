package pcy.study.sns.common.security.authorization;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import pcy.study.sns.common.security.token.JwtProvider;

@RequiredArgsConstructor
public class AuthorizationConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtProvider jwtProvider;
    private final UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity builder) {
        builder.addFilterBefore(new JwtFilter(jwtProvider, userDetailsService), AuthorizationFilter.class);
    }
}
