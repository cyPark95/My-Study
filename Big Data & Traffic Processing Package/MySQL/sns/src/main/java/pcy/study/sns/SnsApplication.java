package pcy.study.sns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SnsApplication {

    // TODO
    //  1. OAuth2
    //  2. 팔로우 승인, 취소 / 댓글 구현
    //  3. MySQL Master / Slave
    public static void main(String[] args) {
        SpringApplication.run(SnsApplication.class, args);
    }

}
