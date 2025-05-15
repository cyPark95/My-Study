package pcy.study.springmvcframe.mvc.annotation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MediaType {

    APPLICATION_JSON_VALUE("application/json"),
    ;

    private final String type;
}
