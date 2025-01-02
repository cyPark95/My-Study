package pcy.study.springmvcframe.mvc.handler.adapter;

import pcy.study.springmvcframe.mvc.annotation.RestController;
import pcy.study.springmvcframe.mvc.handler.HandlerExecution;
import pcy.study.springmvcframe.mvc.view.JsonView;
import pcy.study.springmvcframe.mvc.view.View;

import java.lang.annotation.Annotation;
import java.util.Map;

public class JsonHandlerAdapter extends AnnotationHandlerAdapter {

    @Override
    protected Class<? extends Annotation> getAnnotation() {
        return RestController.class;
    }

    @Override
    protected View getView(HandlerExecution handlerExecution, Map<String, String> paramMap, Map<String, Object> model) throws Exception {
        Object data = handlerExecution.handle(paramMap, model);
        return new JsonView(data);
    }
}
