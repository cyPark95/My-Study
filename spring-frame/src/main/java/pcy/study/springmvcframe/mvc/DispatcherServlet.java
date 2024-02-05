package pcy.study.springmvcframe.mvc;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import pcy.study.springmvcframe.mvc.handler.adapter.HandlerAdapter;
import pcy.study.springmvcframe.mvc.handler.adapter.HandlerAdapterFactory;
import pcy.study.springmvcframe.mvc.handler.mapping.HandlerMappingFactory;
import pcy.study.springmvcframe.mvc.view.ModelAndView;

@Slf4j
@WebServlet(name = "frontController", urlPatterns = "/front/*")
public class DispatcherServlet extends HttpServlet {

    private final HandlerMappingFactory handlerMappingFactory;
    private final HandlerAdapterFactory handlerAdapterFactory;

    public DispatcherServlet() {
        this.handlerMappingFactory = AppConfig.handlerMappingFactory();
        this.handlerAdapterFactory = AppConfig.handlerAdapterFactory();
    }

    @Override
    public void init() {
        handlerMappingFactory.init();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        log.info("Method : {}, Request URI : {}", request.getMethod(), request.getRequestURI());

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
