package pcy.study.springmvcframe.mvc.handler.mapping;

import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class AnnotationScanner {

    private final Reflections reflections;

    public AnnotationScanner(Reflections reflections) {
        this.reflections = reflections;
    }

    public Map<Class<?>, Object> getControllers(Class<? extends Annotation> annotation) {
        return instantiateControllers(reflections.getTypesAnnotatedWith(annotation));
    }

    private Map<Class<?>, Object> instantiateControllers(Set<Class<?>> classes) {
        return classes.stream()
                .collect(Collectors.toMap(controller -> controller, this::getInstance));
    }

    private Object getInstance(Class<?> aClass) {
        try {
            return aClass.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | SecurityException | InstantiationException |
                 IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("인스턴스를 찾을 수 없습니다.");
        }
    }
}
