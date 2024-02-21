package pcy.study.api.common.api;

import lombok.Builder;

@Builder
public record ApiResult(
        Integer code,
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
