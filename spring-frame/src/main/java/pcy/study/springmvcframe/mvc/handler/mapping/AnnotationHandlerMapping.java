package pcy.study.springmvcframe.mvc.handler.mapping;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import pcy.study.springmvcframe.mvc.annotation.RequestMapping;
import pcy.study.springmvcframe.mvc.annotation.RequestMethod;
import pcy.study.springmvcframe.mvc.handler.HandlerExecution;
import pcy.study.springmvcframe.mvc.handler.HandlerKey;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
public class AnnotationHandlerMapping implements HandlerMapping {

    private static final Class<RequestMapping> REQUEST_MAPPING_CLASS = RequestMapping.class;
    private static final String URL_FORMAT = "/front%s";

    private final Object[] basePackages;
    private final Map<HandlerKey, HandlerExecution> handlerExecutions;

    public AnnotationHandlerMapping(Object... basePackages) {
        this.basePackages = basePackages;
        this.handlerExecutions = new HashMap<>();
    }

    @Override
    public void initialize() {
        Reflections reflections = new Reflections(basePackages);
        ControllerScanner scanner = new ControllerScanner(reflections);

        for (Map.Entry<Class<?>, Object> entry : scanner.getControllers().entrySet()) {
            Set<Method> mappingMethods = getAllRequestMappingMethods(entry.getKey());
            this.handlerExecutions.putAll(getHandlerExecutionMap(entry.getValue(), mappingMethods));
        }
    }

    private Set<Method> getAllRequestMappingMethods(Class<?> clazz) {
        return getAllMethods(clazz, ReflectionUtils.withAnnotation(REQUEST_MAPPING_CLASS));
    }

    @SafeVarargs
    private Set<Method> getAllMethods(Class<?> controllerClass, Predicate<? super Method>... predicates) {
        return ReflectionUtils.getAllMethods(controllerClass, predicates);
    }

    private Map<HandlerKey, HandlerExecution> getHandlerExecutionMap(Object handler, Set<Method> mappingMethods) {
        return mappingMethods.stream()
                .map(method -> getHandlerExecutions(handler, method))
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }

    private Map<HandlerKey, HandlerExecution> getHandlerExecutions(Object handler, Method method) {
        HandlerExecution handlerExecution = new HandlerExecution(method, handler);
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
