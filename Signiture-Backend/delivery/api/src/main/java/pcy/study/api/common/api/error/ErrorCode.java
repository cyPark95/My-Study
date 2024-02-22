package pcy.study.api.common.api.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import pcy.study.api.common.api.ApiCode;

@Getter
@RequiredArgsConstructor
public enum ErrorCode implements ApiCode {

    BAD_REQUEST(HttpStatus.BAD_REQUEST, 400, "클라이언트 오류"),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "서버 오류"),
    ACCESS_DENIED(HttpStatus.UNAUTHORIZED, 401, "인증 실패 오류"),
    NOT_FOUNT(HttpStatus.NOT_FOUND, 404, "리소스 접근 오류"),

    NULL_POINT(HttpStatus.INTERNAL_SERVER_ERROR, 512, "Null Point"),
    ;

    private final HttpStatus httpStatus;
    private final Integer code;
    private final String message;
}
