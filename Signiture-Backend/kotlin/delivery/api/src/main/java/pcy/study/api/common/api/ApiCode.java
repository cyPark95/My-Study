package pcy.study.api.common.api;

import org.springframework.http.HttpStatus;

public interface ApiCode {

    HttpStatus getHttpStatus();
    Integer getCode();
    String getMessage();
}
