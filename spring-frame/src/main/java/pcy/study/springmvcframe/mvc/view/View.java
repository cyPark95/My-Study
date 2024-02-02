package pcy.study.springmvcframe.mvc.view;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public interface View {

    void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
