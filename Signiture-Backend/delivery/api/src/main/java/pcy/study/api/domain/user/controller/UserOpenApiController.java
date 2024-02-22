package pcy.study.api.domain.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pcy.study.api.common.api.ApiResponse;
import pcy.study.api.domain.user.business.UserBusiness;
import pcy.study.api.domain.user.controller.model.UserRegisterRequest;
import pcy.study.api.domain.user.controller.model.UserResponse;

@RestController
@RequestMapping("/open-api/users")
@RequiredArgsConstructor
public class UserOpenApiController {

    private final UserBusiness userBusiness;

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> register(@RequestBody @Valid UserRegisterRequest request) {
        var response = userBusiness.register(request);
        return ApiResponse.ok(response);
    }
}
