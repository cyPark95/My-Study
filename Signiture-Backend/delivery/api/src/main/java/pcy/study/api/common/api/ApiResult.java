package pcy.study.api.common.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record ApiResult(
        @Schema(description = "코드")
        Integer code,
        @Schema(description = "메시지")
        String message
) {

    public static ApiResult ok() {
        return create(ResponseCode.OK);
    }

    public static ApiResult error(ApiCode apiCode) {
        return create(apiCode);
    }

    public static ApiResult error(Integer code, String message) {
        return create(code, message);
    }

    private static ApiResult create(ApiCode apiCode) {
        return create(apiCode.getCode(), apiCode.getMessage());
    }

    private static ApiResult create(Integer code, String message) {
        return ApiResult.builder()
                .code(code)
                .message(message)
                .build();
    }
}
