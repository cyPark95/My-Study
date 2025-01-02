package pcy.study.springmvcframe.mvc.handler.adapter;

import pcy.study.springmvcframe.mvc.annotation.Controller;
import pcy.study.springmvcframe.mvc.handler.HandlerExecution;
import pcy.study.springmvcframe.mvc.view.JspView;
import pcy.study.springmvcframe.mvc.view.View;

import java.lang.annotation.Annotation;
import java.util.Map;

public class JspHandlerAdapter extends AnnotationHandlerAdapter {

    @Override
    protected Class<? extends Annotation> getAnnotation() {
        return Controller.class;
    }

    @Override
    protected View getView(HandlerExecution handlerExecution, Map<String, String> paramMap, Map<String, Object> model) throws Exception {
        String viewName = (String) handlerExecution.handle(paramMap, model);
        return new JspView(viewName);
    }
}
