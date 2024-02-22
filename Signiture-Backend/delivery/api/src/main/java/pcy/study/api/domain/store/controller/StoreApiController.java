package pcy.study.api.domain.store.controller;

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
import pcy.study.api.domain.store.business.StoreBusiness;
import pcy.study.api.domain.store.controller.model.StoreResponse;
import pcy.study.db.store.enums.StoreCategory;

import java.util.List;

@Tag(name = "가게 API", description = "인증된 사용자가 접근할 수 있습니다.")
@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreApiController {

    private final StoreBusiness storeBusiness;

    @Operation(summary = "가게 조회", description = "카테고리에 포함된 가게 목록을 조회합니다.")
    @Parameter(name = "category", description = "카테고리", required = true)
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<StoreResponse>>> search(@RequestParam StoreCategory category) {
        var response = storeBusiness.searchByCategory(category);
        return ApiResponse.ok(response);
    }
}
