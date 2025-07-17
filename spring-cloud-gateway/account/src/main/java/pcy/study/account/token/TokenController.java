package pcy.study.account.token;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenController {

    private final pcy.study.authentication.token.TokenProvider tokenProvider;

    @GetMapping("/authentication/token")
    public String getToken(@RequestParam("username") String username) {
        return tokenProvider.generateToken(username);
    }

    @PostMapping("/authentication/username")
    public String getUsername(String token) {
        return tokenProvider.extractUsername(token);
    }
}
