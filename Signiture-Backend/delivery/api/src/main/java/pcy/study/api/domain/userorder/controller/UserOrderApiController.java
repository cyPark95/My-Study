package pcy.study.api.domain.userorder.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pcy.study.api.common.annotation.UserSession;
import pcy.study.api.common.api.ApiResponse;
import pcy.study.api.domain.user.model.UserDetails;
import pcy.study.api.domain.userorder.business.UserOrderBusiness;
import pcy.study.api.domain.userorder.controller.model.UserOrderRequest;
import pcy.study.api.domain.userorder.controller.model.UserOrderResponse;

@Tag(name = "메뉴 주문 API", description = "인증된 사용자가 접근할 수 있습니다.")
@RestController
@RequestMapping("/api/user-orders")
@RequiredArgsConstructor
public class UserOrderApiController {

    private final UserOrderBusiness userOrderBusiness;

    @Operation(summary = "메뉴 주문", description = "메뉴를 주문합니다.")
    @PostMapping
    public ResponseEntity<ApiResponse<UserOrderResponse>> userOrder(
            @UserSession UserDetails userDetails,
            @RequestBody @Valid UserOrderRequest request
    ) {
        var response = userOrderBusiness.userOrder(userDetails, request);
        return ApiResponse.ok(response);
    }
}
