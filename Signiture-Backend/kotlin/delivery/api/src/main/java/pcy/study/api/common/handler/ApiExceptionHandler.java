package pcy.study.api.common.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pcy.study.api.common.api.ApiResponse;
import pcy.study.api.common.exception.ApiException;

@Slf4j
@RestControllerAdvice
@Order(Integer.MIN_VALUE)
public class ApiExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse<Void>> apiExceptionHandler(ApiException e) {
        log.error("[ApiException] message = {}", e.getMessage(), e);
        return ApiResponse.error(e.getApiCode());
    }
}
