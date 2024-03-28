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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import pcy.study.sns.common.security.authentication.AuthenticationConfigurer;
import pcy.study.sns.common.security.authentication.LoginFailureHandler;
import pcy.study.sns.common.security.authentication.LoginFilter;
import pcy.study.sns.common.security.authentication.LoginSuccessHandler;
import pcy.study.sns.common.security.token.JwtProvider;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

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
                .sessionManagement(sessionConfigurer -> sessionConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorization -> authorization
                        .requestMatchers(HttpMethod.POST, "/login", "/members").permitAll()
                        .anyRequest().authenticated()
                )
                .with(getAuthenticationConfigurer(), Customizer.withDefaults());

        return http.build();
    }

    private SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> getAuthenticationConfigurer() {
        return new AuthenticationConfigurer(
                new LoginFilter(objectMapper),
                new LoginSuccessHandler(jwtProvider, objectMapper),
                new LoginFailureHandler()
        );
    }
}
