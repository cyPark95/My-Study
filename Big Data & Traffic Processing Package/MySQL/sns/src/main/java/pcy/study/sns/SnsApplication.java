package pcy.study.sns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SnsApplication {

    // TODO
    //  1. OAuth2
    public static void main(String[] args) {
        SpringApplication.run(SnsApplication.class, args);
    }

}
