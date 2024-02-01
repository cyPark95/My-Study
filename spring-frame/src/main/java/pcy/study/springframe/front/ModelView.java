package pcy.study.springframe.front;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ModelView {

    private final String viewName;
    private Map<String, Object> model = new HashMap<>();

    public ModelView(String viewName) {
        this.viewName = viewName;
    }

    public ModelView(String viewName, Map<String, Object> model) {
        this.viewName = viewName;
        this.model = model;
    }
}
