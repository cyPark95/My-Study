package pcy.study.springmvcframe.mvc.handler.mapping;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import pcy.study.springmvcframe.mvc.annotation.RequestMapping;
import pcy.study.springmvcframe.mvc.annotation.RequestMethod;
import pcy.study.springmvcframe.mvc.handler.AnnotationHandlerExecution;
import pcy.study.springmvcframe.mvc.handler.HandlerExecution;
import pcy.study.springmvcframe.mvc.handler.HandlerKey;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
public class AnnotationHandlerMapping implements HandlerMapping {

    private static final Class<RequestMapping> REQUEST_MAPPING_CLASS = RequestMapping.class;
    private static final String URL_FORMAT = "/front%s";

    private final String basePackages;
    private final List<Class<? extends Annotation>> annotations;
    private final Map<HandlerKey, AnnotationHandlerExecution> handlerExecutions;

    public AnnotationHandlerMapping(String basePackages, List<Class<? extends Annotation>> annotations) {
        this.basePackages = basePackages;
        this.annotations = annotations;
        this.handlerExecutions = new HashMap<>();
    }

    @Override
    public void initialize() {
        Reflections reflections = new Reflections(basePackages);
        AnnotationScanner scanner = new AnnotationScanner(reflections);

        for (Class<? extends Annotation> annotation : annotations) {
            for (Map.Entry<Class<?>, Object> entry : scanner.getControllers(annotation).entrySet()) {
                Set<Method> mappingMethods = getAllRequestMappingMethods(entry.getKey());
                this.handlerExecutions.putAll(getHandlerExecutionMap(annotation, entry.getValue(), mappingMethods));
            }
        }
    }

    private Set<Method> getAllRequestMappingMethods(Class<?> clazz) {
        return getAllMethods(clazz, ReflectionUtils.withAnnotation(REQUEST_MAPPING_CLASS));
    }

    @SafeVarargs
    private Set<Method> getAllMethods(Class<?> controllerClass, Predicate<? super Method>... predicates) {
        return ReflectionUtils.getAllMethods(controllerClass, predicates);
    }

    private Map<HandlerKey, AnnotationHandlerExecution> getHandlerExecutionMap(Class<? extends Annotation> annotation, Object handler, Set<Method> mappingMethods) {
        return mappingMethods.stream()
                .map(method -> getHandlerExecutions(annotation, handler, method))
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }

    private Map<HandlerKey, AnnotationHandlerExecution> getHandlerExecutions(Class<? extends Annotation> annotation, Object handler, Method method) {
        AnnotationHandlerExecution handlerExecution = new AnnotationHandlerExecution(annotation, new HandlerExecution(method, handler));
        RequestMapping requestMapping = method.getAnnotation(REQUEST_MAPPING_CLASS);
        log.info("Path : {}, Method : {}", requestMapping.value(), requestMapping.method());

        return Arrays.stream(requestMapping.method())
                .map(status -> new HandlerKey(String.format(URL_FORMAT, requestMapping.value()), status))
                .collect(Collectors.toMap(Function.identity(), key -> handlerExecution));
    }

    @Override
    public Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        RequestMethod method = RequestMethod.valueOf(request.getMethod());
        return handlerExecutions.get(new HandlerKey(requestURI, method));
    }
}
