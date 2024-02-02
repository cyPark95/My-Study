package pcy.study.springmvcframe.mvc.view;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Slf4j
public class JsonView implements View {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter writer = response.getWriter();

        try {
            String json = toJsonString(model);
            writer.write(json);
        } catch (JsonProcessingException e) {
            log.error("Json Parsing Error Message = {}", e.getMessage());
        }
    }

    private String toJsonString(Map<String, ?> model) throws JsonProcessingException {
        if (model.isEmpty()) {
            return "";
        }
        return OBJECT_MAPPER.writeValueAsString(model);
    }
}
