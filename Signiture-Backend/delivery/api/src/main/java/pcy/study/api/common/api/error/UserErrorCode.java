package pcy.study.api.common.api.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import pcy.study.api.common.api.ApiCode;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ApiCode {

    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, 1404, "사용자를 찾을 수 없습니다."),
    ;

    private final HttpStatus httpStatus;
    private final Integer code;
    private final String message;
}
