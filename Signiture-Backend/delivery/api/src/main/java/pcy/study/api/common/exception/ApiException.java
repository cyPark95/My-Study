package pcy.study.api.common.exception;

import lombok.Getter;
import pcy.study.api.common.api.ApiCode;

@Getter
public class ApiException extends RuntimeException {

    private final ApiCode apiCode;

    public ApiException(ApiCode apiCode) {
        super(apiCode.getMessage());
        this.apiCode = apiCode;
    }

    public ApiException(String message, ApiCode apiCode) {
        super(message);
        this.apiCode = apiCode;
    }
}
