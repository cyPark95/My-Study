package pcy.study.springmvcframe.mvc.handler.mapping;

import jakarta.servlet.http.HttpServletRequest;

public interface HandlerMapping {

    void initialize();

    Object getHandler(HttpServletRequest request);
}
