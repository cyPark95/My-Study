package pcy.study.springmvcframe.mvc.adapter;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
public class HandlerAdapterFactory {

    private final List<HandlerAdapter> handlerAdapters;

    public HandlerAdapterFactory() {
        this.handlerAdapters = new ArrayList<>();
    }

    public void addHandlerAdapter(HandlerAdapter handlerAdapter) {
        log.info("Add Handler Adapter = {}", handlerAdapter);

        handlerAdapters.add(handlerAdapter);
    }

    public HandlerAdapter findAdapter(Object handler) {
        log.info("Find Handler {}", handler);

        return handlerAdapters.stream()
                .filter(handlerAdapter -> handlerAdapter.supports(handler))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("일치하는 어댑터를 찾을 수 없습니다."));
    }
}
