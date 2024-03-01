package pcy.study.api.domain.storemenu.controller;

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
import pcy.study.api.domain.storemenu.business.StoreMenuBusiness;
import pcy.study.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import pcy.study.api.domain.storemenu.controller.model.StoreMenuResponse;

@Tag(name = "가게 메뉴 OPEN-API", description = "모든 사용자가 접근할 수 있습니다.")
@RestController
@RequestMapping("/open-api/store-menus")
@RequiredArgsConstructor
public class StoreMenuOpenApiController {

    private final StoreMenuBusiness storeMenuBusiness;

    @Operation(summary = "가게 메뉴 등록", description = "가게의 메뉴 정보를 등록합니다.")
    @PostMapping
    public ResponseEntity<ApiResponse<StoreMenuResponse>> register(@RequestBody @Valid StoreMenuRegisterRequest request) {
        var response = storeMenuBusiness.register(request);
        return ApiResponse.ok(response);
    }
}
