package pcy.study.api.common.handler;

import kotlin.Unit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import pcy.study.common.api.ApiResponse;
import pcy.study.common.api.code.ErrorCode;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse<Unit>> httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
        log.error("[HttpRequestMethodNotSupportedException] Method = {}, SupportedMethods = {}", e.getMethod(), e.getSupportedHttpMethods(), e);
        return new ResponseEntity<>(
                ApiResponse.error(ErrorCode.NOT_FOUNT), HttpStatusCode.valueOf(ErrorCode.NOT_FOUNT.getHttpStatusCode()));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse<Unit>> NoResourceFoundExceptionHandler(NoResourceFoundException e) {
        log.error("[NoResourceFoundException] Method = {}, URL = {}", e.getHttpMethod(), e.getResourcePath(), e);
        return new ResponseEntity<>(
                ApiResponse.error(ErrorCode.METHOD_NOT_ALLOWED),
                HttpStatusCode.valueOf(ErrorCode.METHOD_NOT_ALLOWED.getHttpStatusCode())
        );
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResponse<Unit>> missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e) {
        log.error("[MissingServletRequestParameterException] Parameter Type = {}, Name = {}", e.getParameterType(), e.getParameterName(), e);
        return new ResponseEntity<>(
                ApiResponse.error(ErrorCode.BAD_REQUEST),
                HttpStatusCode.valueOf(ErrorCode.BAD_REQUEST.getHttpStatusCode())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Unit>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("[HandleMethodArgumentNotValidException]", e);
        BindingResult bindingResult = e.getBindingResult();

        List<String> errorMessage = new ArrayList<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMessage.add("[" + fieldError.getField() + "] " + fieldError.getDefaultMessage());
        }

        return new ResponseEntity<>(
                ApiResponse.error(ErrorCode.BAD_REQUEST, String.join("," + System.lineSeparator(), errorMessage)),
                HttpStatusCode.valueOf(ErrorCode.BAD_REQUEST.getHttpStatusCode())
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Unit>> exceptionHandler(Exception e) {
        log.error("[Exception]", e);
        return new ResponseEntity<>(
                ApiResponse.error(ErrorCode.SERVER_ERROR),
                HttpStatusCode.valueOf(ErrorCode.SERVER_ERROR.getHttpStatusCode())
        );
    }
}
