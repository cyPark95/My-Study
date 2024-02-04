package pcy.study.springmvcframe.mvc.handler;

import lombok.ToString;

import java.lang.reflect.Method;
import java.util.Map;

@ToString
public class HandlerExecution {

    private final Method method;
    private final Object handler;

    public HandlerExecution(Method method, Object handler) {
        this.method = method;
        this.handler = handler;
    }

    public String handle(Map<String, String> paramMap, Map<String, Object> model) throws Exception {
        return (String) method.invoke(handler, paramMap, model);
    }
}
