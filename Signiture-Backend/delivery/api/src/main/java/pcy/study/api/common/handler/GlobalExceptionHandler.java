package pcy.study.api.common.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pcy.study.api.common.api.ApiResponse;
import pcy.study.api.common.api.error.ErrorCode;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> exceptionHandler(Exception e) {
        log.error("[Exception] ", e);
        return ApiResponse.error(ErrorCode.SERVER_ERROR);
    }
}
