package pcy.study.springframe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pcy.study.springframe.controller.Controller;
import pcy.study.springframe.controller.MemberFormController;
import pcy.study.springframe.controller.MemberListController;
import pcy.study.springframe.controller.MemberSaveController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class SpringFrameApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringFrameApplication.class, args);
    }

}
