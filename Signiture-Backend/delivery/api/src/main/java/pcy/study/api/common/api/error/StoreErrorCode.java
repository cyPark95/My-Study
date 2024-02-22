package pcy.study.api.common.api.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import pcy.study.api.common.api.ApiCode;

@Getter
@RequiredArgsConstructor
public enum StoreErrorCode implements ApiCode {

    STORE_NOT_FOUND(HttpStatus.BAD_REQUEST, 3001, "가게를 찾을 수 없습니다."),
    ;

    private final HttpStatus httpStatus;
    private final Integer code;
    private final String message;
}
