package pcy.study.sns.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import pcy.study.sns.common.security.authentication.*;
import pcy.study.sns.common.security.authorization.AccessDeniedExceptionHandler;
import pcy.study.sns.common.security.authorization.AuthorizationConfigurer;
import pcy.study.sns.common.security.token.JwtProvider;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserDetailsService userDetailsService;
    private final JwtProvider jwtProvider;
    private final ObjectMapper objectMapper;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(withDefaults())
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers(HttpMethod.GET, "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login", "/members").permitAll()
                        .anyRequest().authenticated()
                )
                .with(getAuthenticationConfigurer(), Customizer.withDefaults())
                .with(getAuthorizationConfigurer(), Customizer.withDefaults())
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(new AuthenticationExceptionHandler())
                        .accessDeniedHandler(new AccessDeniedExceptionHandler())
                );

        return http.build();
    }

    private SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> getAuthenticationConfigurer() {
        return new AuthenticationConfigurer(
                new LoginFilter(objectMapper),
                new LoginSuccessHandler(jwtProvider, objectMapper),
                new LoginFailureHandler()
        );
    }

    private SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> getAuthorizationConfigurer() {
        return new AuthorizationConfigurer(jwtProvider, userDetailsService);
    }
}
