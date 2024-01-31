package pcy.study.springframe.controller;

import jakarta.servlet.ServletException;

import java.io.IOException;
import java.util.Map;

public interface ParamController {

    String process(Map<String, String> paramMap, Map<String, Object> model) throws ServletException, IOException;
}
