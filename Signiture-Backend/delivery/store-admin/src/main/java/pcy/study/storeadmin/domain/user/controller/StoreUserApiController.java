package pcy.study.storeadmin.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pcy.study.storeadmin.domain.authorization.model.StoreUserDetails;
import pcy.study.storeadmin.domain.user.business.StoreUserBusiness;
import pcy.study.storeadmin.domain.user.controller.model.StoreUserResponse;

@Tag(name = "가맹점 사용자 API", description = "인증된 사용자가 접근할 수 있습니다.")
@RestController
@RequestMapping("/api/store-users")
@RequiredArgsConstructor
public class StoreUserApiController {

    private final StoreUserBusiness storeUserBusiness;

    @Operation(summary = "가맹점 사용자 정보 조회", description = "로그인된 사용자의 본인 정보를 조회합니다.")
    @GetMapping("/me")
    public StoreUserResponse me(
            @Parameter(hidden = true)
            @AuthenticationPrincipal StoreUserDetails storeUserDetails
    ) {
        return storeUserBusiness.authorization(storeUserDetails);
    }
}
