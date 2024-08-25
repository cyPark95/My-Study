package pcy.study.api.common.api.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import pcy.study.api.common.api.ApiCode;

@Getter
@RequiredArgsConstructor
public enum StoreMenuErrorCode implements ApiCode {

    STORE_MENU_NOT_FOUND(HttpStatus.BAD_REQUEST, 4001, "가게 메뉴를 찾을 수 없습니다."),
    ;

    private final HttpStatus httpStatus;
    private final Integer code;
    private final String message;
}
