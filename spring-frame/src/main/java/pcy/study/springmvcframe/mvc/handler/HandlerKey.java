package pcy.study.springmvcframe.mvc.handler;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import pcy.study.springmvcframe.mvc.annotation.RequestMethod;

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
