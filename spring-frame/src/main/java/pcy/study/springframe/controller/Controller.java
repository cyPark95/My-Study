package pcy.study.springframe.controller;

import jakarta.servlet.ServletException;
import pcy.study.springframe.front.ModelView;

import java.io.IOException;
import java.util.Map;

public interface Controller {

    ModelView process(Map<String, String> paramMap) throws ServletException, IOException;
}
