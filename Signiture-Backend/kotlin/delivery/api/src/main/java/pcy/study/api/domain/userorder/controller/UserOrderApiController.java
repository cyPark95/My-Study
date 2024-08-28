package pcy.study.api.domain.userorder.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pcy.study.api.domain.user.model.UserDetails;
import pcy.study.api.domain.userorder.business.UserOrderBusiness;
import pcy.study.api.domain.userorder.controller.model.UserOrderDetailResponse;
import pcy.study.api.domain.userorder.controller.model.UserOrderRequest;
import pcy.study.api.domain.userorder.controller.model.UserOrderResponse;
import pcy.study.common.annotation.UserSession;
import pcy.study.common.api.ApiResponse;

import java.util.List;

@Tag(name = "메뉴 주문 API", description = "인증된 사용자가 접근할 수 있습니다.")
@RestController
@RequestMapping("/api/user-orders")
@RequiredArgsConstructor
public class UserOrderApiController {

    private final UserOrderBusiness userOrderBusiness;

    @Operation(summary = "메뉴 주문", description = "메뉴를 주문합니다.")
    @PostMapping
    public ApiResponse<UserOrderResponse> userOrder(
            @Parameter(hidden = true)
            @UserSession UserDetails userDetails,
            @RequestBody @Valid UserOrderRequest request
    ) {
        var response = userOrderBusiness.userOrder(userDetails, request);
        return ApiResponse.ok(response);
    }

    @Operation(summary = "현재 주문 내역 조회", description = "현재 진행중인 주문 메뉴 목록을 조회합니다.")
    @GetMapping("/current")
    public ApiResponse<List<UserOrderDetailResponse>> current(
            @Parameter(hidden = true)
            @UserSession UserDetails userDetails
    ) {
        var response = userOrderBusiness.current(userDetails);
        return ApiResponse.ok(response);
    }

    @Operation(summary = "과거 주문 내역 조회", description = "과거에 주문했던 메뉴 목록을 조회합니다.")
    @GetMapping("/history")
    public ApiResponse<List<UserOrderDetailResponse>> history(
            @Parameter(hidden = true)
            @UserSession UserDetails userDetails
    ) {
        var response = userOrderBusiness.history(userDetails);
        return ApiResponse.ok(response);
    }

    @Operation(summary = "주문 조회", description = "주문 단건을 조회합니다.")
    @Parameter(name = "id", description = "주문 식별값", required = true)
    @GetMapping("/{id}")
    public ApiResponse<UserOrderDetailResponse> read(
            @Parameter(hidden = true)
            @UserSession UserDetails userDetails,
            @PathVariable("id") Long id
    ) {
        var response = userOrderBusiness.read(userDetails, id);
        return ApiResponse.ok(response);
    }
}
