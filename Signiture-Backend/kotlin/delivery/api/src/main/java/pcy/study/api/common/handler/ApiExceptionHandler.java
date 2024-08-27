package pcy.study.api.common.handler;

import kotlin.Unit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pcy.study.common.api.ApiResponse;
import pcy.study.common.exception.ApiException;

@Slf4j
@RestControllerAdvice
@Order(Integer.MIN_VALUE)
public class ApiExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse<Unit>> apiExceptionHandler(ApiException e) {
        log.error("[ApiException] message = {}", e.getMessage(), e);
        return new ResponseEntity<>(
                ApiResponse.error(e.getApiCode()),
                HttpStatus.valueOf(e.getApiCode().getHttpStatusCode())
        );
    }
}
