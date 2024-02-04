package pcy.study.springmvcframe.mvc.handler.mapping;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Slf4j
public class HandlerMappingFactory {

    private final List<HandlerMapping> handlerMappings;

    public HandlerMappingFactory() {
        this.handlerMappings = new ArrayList<>();
    }

    public void addHandlerMapping(HandlerMapping handlerMapping) {
        handlerMappings.add(handlerMapping);
    }

    public Object findHandler(HttpServletRequest request) {
        return handlerMappings.stream()
                .map(handlerMapping -> handlerMapping.getHandler(request))
                .toList().stream()
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("일치하는 핸들러가 없습니다."));
    }

    public void init() {
        handlerMappings.forEach(HandlerMapping::initialize);
    }
}
