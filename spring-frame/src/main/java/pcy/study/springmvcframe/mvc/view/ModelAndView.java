package pcy.study.springmvcframe.mvc.view;

import lombok.Getter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Getter
public class ModelAndView {

    private final View view;
    private final Map<String, Object> model;

    public ModelAndView(View view) {
        this.view = view;
        model = new HashMap<>();
    }

    public void addAttribute(String key, Object value) {
        model.put(key, value);
    }

    public Object getAttribute(String key) {
        return model.get(key);
    }

    public Map<String, Object> getModel() {
        return Collections.unmodifiableMap(model);
    }
}
