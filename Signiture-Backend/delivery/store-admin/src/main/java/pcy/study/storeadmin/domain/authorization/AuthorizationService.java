package pcy.study.storeadmin.domain.authorization;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pcy.study.storeadmin.domain.user.service.StoreUserService;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private final StoreUserService storeUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var storeUser = storeUserService.getUser(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Email: [%s] StoreUser Not Found", username)));
        return User.builder()
                .username(storeUser.getEmail())
                .password(storeUser.getPassword())
                .roles(storeUser.getRole().name())
                .build();
    }
}
