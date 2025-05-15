package pcy.study.springmvcframe.mvc.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import pcy.study.springmvcframe.mvc.annotation.MediaType;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
public class JsonView implements View {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final Object data;

    public JsonView(Object data) {
        this.data = data;
    }

    @Override
    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE.getType());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        PrintWriter writer = response.getWriter();
        OBJECT_MAPPER.writeValue(writer, data);
    }
}
