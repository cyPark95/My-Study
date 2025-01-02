package pcy.study.springmvcframe.mvc;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pcy.study.springmvcframe.mvc.annotation.Controller;
import pcy.study.springmvcframe.mvc.annotation.RestController;
import pcy.study.springmvcframe.mvc.handler.adapter.HandlerAdapterFactory;
import pcy.study.springmvcframe.mvc.handler.adapter.JsonHandlerAdapter;
import pcy.study.springmvcframe.mvc.handler.adapter.JspHandlerAdapter;
import pcy.study.springmvcframe.mvc.handler.mapping.AnnotationHandlerMapping;
import pcy.study.springmvcframe.mvc.handler.mapping.HandlerMappingFactory;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AppConfig {

    private static final String BASE_PACKAGE = "pcy.study.springmvcframe.app";
    public static final List<Class<? extends Annotation>> CLASSES = List.of(Controller.class, RestController.class);

    private static HandlerMappingFactory handlerMappingFactory;
    private static HandlerAdapterFactory handlerAdapterFactory;

    public static HandlerMappingFactory handlerMappingFactory() {
        if (Objects.isNull(handlerMappingFactory)) {
            handlerMappingFactory = new HandlerMappingFactory();
            handlerMappingFactory.addHandlerMapping(new AnnotationHandlerMapping(BASE_PACKAGE, CLASSES));
        }
        return handlerMappingFactory;
    }

    public static HandlerAdapterFactory handlerAdapterFactory() {
        if (Objects.isNull(handlerAdapterFactory)) {
            handlerAdapterFactory = new HandlerAdapterFactory();
            handlerAdapterFactory.addHandlerAdapter(new JspHandlerAdapter());
            handlerAdapterFactory.addHandlerAdapter(new JsonHandlerAdapter());
        }
        return handlerAdapterFactory;
    }
}
