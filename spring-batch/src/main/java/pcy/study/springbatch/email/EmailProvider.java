package pcy.study.springbatch.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

public interface EmailProvider {

    void send(String email, String title, String content);

    @Slf4j
    @Component
    class Fake implements EmailProvider {

        @Override
        public void send(String email, String title, String content) {
            log.info("{} Email 전송 완료! [{}] {}", email, title, content);
        }
    }
}
