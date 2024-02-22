package pcy.study.api.common.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import pcy.study.api.common.api.ApiResponse;
import pcy.study.api.common.api.error.ErrorCode;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse<Void>> httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
        log.error("[HttpRequestMethodNotSupportedException] Method = {}, SupportedMethods = {}", e.getMethod(), e.getSupportedHttpMethods(), e);
        return ApiResponse.error(ErrorCode.NOT_FOUNT);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse<Void>> NoResourceFoundExceptionHandler(NoResourceFoundException e) {
        log.error("[NoResourceFoundException] Method = {}, URL = {}", e.getHttpMethod(), e.getResourcePath(), e);
        return ApiResponse.error(ErrorCode.NOT_FOUNT);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResponse<Void>> missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e) {
        log.error("[MissingServletRequestParameterException] Parameter Type = {}, Name = {}", e.getParameterType(), e.getParameterName(), e);
        return ApiResponse.error(ErrorCode.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("[HandleMethodArgumentNotValidException]", e);
        BindingResult bindingResult = e.getBindingResult();

        List<String> errorMessage = new ArrayList<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMessage.add("[" + fieldError.getField() + "] " + fieldError.getDefaultMessage());
        }

        return ApiResponse.error(ErrorCode.BAD_REQUEST, String.join("," + System.lineSeparator(), errorMessage));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> exceptionHandler(Exception e) {
        log.error("[Exception]", e);
        return ApiResponse.error(ErrorCode.SERVER_ERROR);
    }
}
