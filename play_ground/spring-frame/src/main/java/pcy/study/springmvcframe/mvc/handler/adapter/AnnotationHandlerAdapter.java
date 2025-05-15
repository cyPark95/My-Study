package pcy.study.springmvcframe.mvc.handler.adapter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pcy.study.springmvcframe.mvc.handler.AnnotationHandlerExecution;
import pcy.study.springmvcframe.mvc.handler.HandlerExecution;
import pcy.study.springmvcframe.mvc.view.ModelAndView;
import pcy.study.springmvcframe.mvc.view.View;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

public abstract class AnnotationHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        if (handler instanceof AnnotationHandlerExecution handlerExecution) {
            return handlerExecution.getType() == getAnnotation();
        }
        return false;
    }

    protected abstract Class<? extends Annotation> getAnnotation();

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AnnotationHandlerExecution annotationHandlerExecution = (AnnotationHandlerExecution) handler;
        HandlerExecution handlerExecution = annotationHandlerExecution.getHandlerExecution();
        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();
        View view = getView(handlerExecution, paramMap, model);
        return new ModelAndView(view, model);
    }

    protected abstract View getView(HandlerExecution handlerExecution, Map<String, String> paramMap, Map<String, Object> model) throws Exception;

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
