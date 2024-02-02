package pcy.study.springmvcframe.mvc.controller;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.web.bind.annotation.RequestMethod;

@ToString
@EqualsAndHashCode
public class HandlerKey {

    private final String url;
    private final RequestMethod requestMethod;

    public HandlerKey(String url, RequestMethod requestMethod) {
        this.url = url;
        this.requestMethod = requestMethod;
    }
}
