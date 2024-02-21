package pcy.study.api.common.api;

import lombok.Builder;

@Builder
public record ApiResult(
        Integer status,
        Integer code,
        String description
) {

    public static ApiResult ok() {
        return create(ResponseCode.OK);
    }

    private static ApiResult create(ResponseCode responseCode) {
        return ApiResult.builder()
                .status(responseCode.getHttpStatus().value())
                .code(responseCode.getCode())
                .description(responseCode.getDescription())
                .build();
    }
}
