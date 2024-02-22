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

    private static ApiResult create(ApiCode apiCode) {
        return ApiResult.builder()
                .code(apiCode.getCode())
                .message(apiCode.getMessage())
                .build();
    }
}
