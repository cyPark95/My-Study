package pcy.study.api.common.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.springframework.http.ResponseEntity;

@Builder
public record ApiResponse<T>(
        @Schema(description = "결과")
        ApiResult result,
        @Schema(description = "데이터")
        T body
) {

    public static <T> ResponseEntity<ApiResponse<T>> ok(T data) {
        return ResponseEntity.ok(create(ApiResult.ok(), data));
    }

    public static ResponseEntity<ApiResponse<Void>> error(ApiCode apiCode) {
        return new ResponseEntity<>(create(ApiResult.error(apiCode)), apiCode.getHttpStatus());
    }

    public static ResponseEntity<ApiResponse<Void>> error(ApiCode apiCode, String message) {
        return new ResponseEntity<>(create(ApiResult.error(apiCode.getCode(), message)), apiCode.getHttpStatus());
    }

    private static ApiResponse<Void> create(ApiResult result) {
        return create(result, null);
    }

    private static <T> ApiResponse<T> create(ApiResult result, T data) {
        return ApiResponse.<T>builder()
                .result(result)
                .body(data)
                .build();
    }
}
