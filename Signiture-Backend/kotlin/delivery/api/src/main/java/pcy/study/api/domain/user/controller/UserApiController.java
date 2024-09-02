package pcy.study.api.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pcy.study.api.domain.user.business.UserBusiness;
import pcy.study.api.domain.user.controller.model.UserResponse;
import pcy.study.api.domain.user.model.UserDetails;
import pcy.study.common.annotation.UserSession;
import pcy.study.common.api.ApiResponse;

@Tag(name = "사용자 API", description = "인증된 사용자가 접근할 수 있습니다.")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApiController {

    private final UserBusiness userBusiness;

    @Operation(summary = "사용자 정보 조회", description = "로그인된 사용자의 본인 정보를 조회합니다.")
    @GetMapping("/me")
    public ApiResponse<UserResponse> me(
            @Parameter(hidden = true)
            @UserSession UserDetails userDetails
    ) {
        UserResponse response = userBusiness.info(userDetails.getId());
        return ApiResponse.ok(response);
    }
}
