package pcy.study.api.common.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ResponseCode implements ApiCode {

    OK(HttpStatus.OK, 0, "성공"),
    ;

    private final HttpStatus httpStatus;
    private final Integer code;
    private final String message;
}
