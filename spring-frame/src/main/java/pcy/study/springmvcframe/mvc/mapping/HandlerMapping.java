package pcy.study.springmvcframe.mvc.mapping;

import jakarta.servlet.http.HttpServletRequest;

public interface HandlerMapping {

    void initialize();

    Object getHandler(HttpServletRequest request);
}
