package pcy.study.springmvcframe.mvc.view;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class JspView implements View {

    private final String viewName;

    public JspView(String viewName) {
        this.viewName = viewResolver(viewName);
    }

    @Override
    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        modelToRequestAttribute(model, request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewName);
        dispatcher.forward(request, response);
    }

    private String viewResolver(String viewName) {
        return "/WEB-INF/views/" + viewName + ".jsp";
    }

    private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        model.forEach(request::setAttribute);
    }
}
