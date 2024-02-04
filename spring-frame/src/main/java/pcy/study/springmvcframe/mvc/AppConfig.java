package pcy.study.springmvcframe.mvc;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pcy.study.springmvcframe.mvc.handler.adapter.ControllerHandlerAdapter;
import pcy.study.springmvcframe.mvc.handler.adapter.HandlerAdapterFactory;
import pcy.study.springmvcframe.mvc.handler.mapping.AnnotationHandlerMapping;
import pcy.study.springmvcframe.mvc.handler.mapping.HandlerMappingFactory;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AppConfig {

    private static final String BASE_PACKAGE = "pcy.study.springmvcframe.app";

    private static HandlerMappingFactory handlerMappingFactory;
    private static HandlerAdapterFactory handlerAdapterFactory;

    public static HandlerMappingFactory handlerMappingFactory() {
        if (Objects.isNull(handlerMappingFactory)) {
            handlerMappingFactory = new HandlerMappingFactory();
            handlerMappingFactory.addHandlerMapping(new AnnotationHandlerMapping(BASE_PACKAGE));
        }
        return handlerMappingFactory;
    }

    public static HandlerAdapterFactory handlerAdapterFactory() {
        if (Objects.isNull(handlerAdapterFactory)) {
            handlerAdapterFactory = new HandlerAdapterFactory();
            handlerAdapterFactory.addHandlerAdapter(new ControllerHandlerAdapter());
        }
        return handlerAdapterFactory;
    }
}
