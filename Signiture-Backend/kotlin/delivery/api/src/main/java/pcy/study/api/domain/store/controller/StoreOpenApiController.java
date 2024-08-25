package pcy.study.api.domain.store.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pcy.study.api.common.api.ApiResponse;
import pcy.study.api.domain.store.business.StoreBusiness;
import pcy.study.api.domain.store.controller.model.StoreRegisterRequest;
import pcy.study.api.domain.store.controller.model.StoreResponse;

@Tag(name = "가게 OPEN-API", description = "모든 사용자가 접근할 수 있습니다.")
@RestController
@RequestMapping("/open-api/stores")
@RequiredArgsConstructor
public class StoreOpenApiController {

    private final StoreBusiness storeBusiness;

    @Operation(summary = "가게 등록", description = "가게 정보를 등록합니다.")
    @PostMapping
    public ResponseEntity<ApiResponse<StoreResponse>> register(@RequestBody @Valid StoreRegisterRequest request){
        var response = storeBusiness.register(request);
        return ApiResponse.ok(response);
    }
}
