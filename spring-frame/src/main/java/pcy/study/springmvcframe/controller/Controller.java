package pcy.study.springmvcframe.controller;

import jakarta.servlet.ServletException;
import pcy.study.springmvcframe.mvc.view.ModelAndView;

import java.io.IOException;
import java.util.Map;

public interface Controller {

    ModelAndView process(Map<String, String> paramMap) throws ServletException, IOException;
}
