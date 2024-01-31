package pcy.study.springframe.controller;

import jakarta.servlet.ServletException;
import pcy.study.springframe.front.ModelView;

import java.io.IOException;
import java.util.Map;

public class MemberFormController implements Controller {

    @Override
    public ModelView process(Map<String, String> paramMap) throws ServletException, IOException {
        return new ModelView("new-form");
    }
}
