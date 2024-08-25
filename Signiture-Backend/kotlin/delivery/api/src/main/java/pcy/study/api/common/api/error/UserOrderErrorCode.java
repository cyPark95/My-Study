package pcy.study.api.common.api.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import pcy.study.api.common.api.ApiCode;

@Getter
@RequiredArgsConstructor
public enum UserOrderErrorCode implements ApiCode {

    USER_ORDER_NOT_FOUND(HttpStatus.BAD_REQUEST, 5001, "주문 정보를 찾을 수 없습니다."),
    ;

    private final HttpStatus httpStatus;
    private final Integer code;
    private final String message;
}
