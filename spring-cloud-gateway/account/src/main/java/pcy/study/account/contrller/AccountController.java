package pcy.study.account.contrller;

import org.springframework.web.bind.annotation.*;
import pcy.study.account.contrller.request.UsernameRequest;
import pcy.study.account.token.TokenProvider;

@RestController
public class AccountController {

    private final TokenProvider tokenProvider;

    public AccountController(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @GetMapping("/token")
    public String getToken(@RequestParam("username") String username) {
        return tokenProvider.generateToken(username);
    }

    @PostMapping("/username")
    public String getUsername(@RequestBody UsernameRequest request) {
        return tokenProvider.extractUsername(request.token());
    }

}
