package pcy.study.springmvcframe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SpringMvcFrameApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcFrameApplication.class, args);
    }

}
