package pcy.study.api.domain.storemenu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pcy.study.api.common.api.ApiResponse;
import pcy.study.api.domain.storemenu.business.StoreMenuBusiness;
import pcy.study.api.domain.storemenu.controller.model.StoreMenuResponse;

import java.util.List;

@Tag(name = "가게 메뉴 API", description = "인증된 사용자가 접근할 수 있습니다.")
@RestController
@RequestMapping("/api/store-menus")
@RequiredArgsConstructor
public class StoreMenuApiController {

    private final StoreMenuBusiness storeMenuBusiness;

    @Operation(summary = "가게 메뉴 조회", description = "가게에 포함된 메뉴 목록을 조회합니다.")
    @Parameter(name = "storeId", description = "가게 식별값", required = true)
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<StoreMenuResponse>>> search(@RequestParam("storeId") Long storeId) {
        var response = storeMenuBusiness.searchByStore(storeId);
        return ApiResponse.ok(response);
    }
}
