package pcy.study.springmvcframe.adapter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pcy.study.springmvcframe.controller.Controller;
import pcy.study.springmvcframe.mvc.view.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerHandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof Controller);
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        Controller controller = (Controller) handler;

        Map<String, String> paramMap = createParamMap(request);
        return controller.process(paramMap);
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
