package pcy.study.api.common.api.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import pcy.study.api.common.api.ApiCode;

@Getter
@RequiredArgsConstructor
public enum TokenErrorCode implements ApiCode {

    TOKEN_EXCEPTION(HttpStatus.BAD_REQUEST, 2000, "알 수 없는 토큰 에러입니다."),
    INVALID_TOKEN(HttpStatus.BAD_REQUEST, 2001, "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(HttpStatus.BAD_REQUEST, 2002, "만료된 토큰입니다."),
    ;

    private final HttpStatus httpStatus;
    private final Integer code;
    private final String message;
}
