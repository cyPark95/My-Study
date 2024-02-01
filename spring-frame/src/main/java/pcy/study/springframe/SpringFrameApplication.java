package pcy.study.springframe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SpringFrameApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringFrameApplication.class, args);
    }

}
