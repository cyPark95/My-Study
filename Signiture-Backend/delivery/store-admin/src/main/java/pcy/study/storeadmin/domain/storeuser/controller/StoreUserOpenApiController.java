package pcy.study.storeadmin.domain.storeuser.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pcy.study.storeadmin.domain.storeuser.business.StoreUserBusiness;
import pcy.study.storeadmin.domain.storeuser.controller.model.StoreUserRegisterRequest;
import pcy.study.storeadmin.domain.storeuser.controller.model.StoreUserResponse;

@Tag(name = "가맹점 사용자 OPEN-API", description = "모든 사용자가 접근할 수 있습니다.")
@RestController
@RequestMapping("/open-api/store-users")
@RequiredArgsConstructor
public class StoreUserOpenApiController {

    private final StoreUserBusiness storeUserBusiness;

    @Operation(summary = "가맹점 사용자 등록", description = "가맹점 서비스를 사용하기 위해 회원 정보를 등록합니다.")
    @PostMapping
    public StoreUserResponse register(@RequestBody @Valid StoreUserRegisterRequest request) {
        return storeUserBusiness.register(request);
    }
}
