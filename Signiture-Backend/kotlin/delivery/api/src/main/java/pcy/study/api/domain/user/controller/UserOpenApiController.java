package pcy.study.api.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pcy.study.api.common.api.ApiResponse;
import pcy.study.api.domain.token.controller.model.TokenResponse;
import pcy.study.api.domain.user.business.UserBusiness;
import pcy.study.api.domain.user.controller.model.UserLoginRequest;
import pcy.study.api.domain.user.controller.model.UserRegisterRequest;
import pcy.study.api.domain.user.controller.model.UserResponse;

@Tag(name = "사용자 OPEN-API", description = "모든 사용자가 접근할 수 있습니다.")
@RestController
@RequestMapping("/open-api/users")
@RequiredArgsConstructor
public class UserOpenApiController {

    private final UserBusiness userBusiness;

    @Operation(summary = "사용자 등록", description = "서비스를 사용하기 위해 회원 정보를 등록합니다.")
    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> register(@RequestBody @Valid UserRegisterRequest request) {
        var response = userBusiness.register(request);
        return ApiResponse.ok(response);
    }

    @Operation(summary = "로그인", description = "인증받기 위한 로그인 기능 입니다.")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenResponse>> login(@RequestBody @Valid UserLoginRequest request) {
        var response = userBusiness.login(request);
        return ApiResponse.ok(response);
    }
}
