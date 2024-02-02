package pcy.study.springmvcframe.mvc.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pcy.study.springmvcframe.mvc.view.ModelAndView;

import java.lang.reflect.Method;

public class HandlerExecution {

    private final Method method;
    private final Object handler;

    public HandlerExecution(Object handler, Method method) {
        this.method = method;
        this.handler = handler;
    }

    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return (ModelAndView) method.invoke(handler, request, response);
    }
}
