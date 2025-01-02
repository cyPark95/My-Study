package pcy.study.springmvcframe.mvc.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.lang.annotation.Annotation;

@Getter
@RequiredArgsConstructor
public class AnnotationHandlerExecution {

    private final Class<? extends Annotation> type;
    private final HandlerExecution handlerExecution;
}
