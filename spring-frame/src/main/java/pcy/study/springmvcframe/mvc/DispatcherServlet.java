package pcy.study.springmvcframe.mvc;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import pcy.study.springmvcframe.mvc.adapter.HandlerAdapter;
import pcy.study.springmvcframe.mvc.adapter.HandlerAdapterFactory;
import pcy.study.springmvcframe.mvc.mapping.HandlerMapping;
import pcy.study.springmvcframe.mvc.mapping.HandlerMappingFactory;
import pcy.study.springmvcframe.mvc.view.ModelAndView;

@Slf4j
public class DispatcherServlet extends HttpServlet {

    private final HandlerMappingFactory handlerMappingFactory;
    private final HandlerAdapterFactory handlerAdapterFactory;

    public DispatcherServlet() {
        this.handlerMappingFactory = new HandlerMappingFactory();
        this.handlerAdapterFactory = new HandlerAdapterFactory();
    }

    @Override
    public void init() {
        handlerMappingFactory.init();
    }

    public void addHandlerMapping(HandlerMapping handlerMapping) {
        handlerMappingFactory.addHandlerMapping(handlerMapping);
    }

    public void addHandlerAdapter(HandlerAdapter handlerAdapter) {
        handlerAdapterFactory.addHandlerAdapter(handlerAdapter);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        log.debug("Method : {}, Request URI : {}", request.getMethod(), request.getRequestURI());
        try {
            Object handler = handlerMappingFactory.findHandler(request);
            HandlerAdapter adapter = handlerAdapterFactory.findAdapter(handler);
            ModelAndView modelAndView = adapter.handle(request, response, handler);
            render(modelAndView, request, response);
        } catch (Throwable e) {
            log.error("Exception : {}", e.getMessage(), e);
            throw new ServletException(e.getMessage());
        }
    }

    public void render(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) throws Exception {
        modelAndView.getView().render(modelAndView.getModel(), request, response);
    }
}
