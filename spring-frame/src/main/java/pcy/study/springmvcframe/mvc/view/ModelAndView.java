package pcy.study.springmvcframe.mvc.view;

import lombok.Getter;

import java.util.Collections;
import java.util.Map;

@Getter
public class ModelAndView {

    private final View view;
    private final Map<String, Object> model;

    public ModelAndView(String viewName, Map<String, Object> model) {
        this.view = new JspView(viewName);
        this.model = model;
    }

    public Map<String, Object> getModel() {
        return Collections.unmodifiableMap(model);
    }
}
