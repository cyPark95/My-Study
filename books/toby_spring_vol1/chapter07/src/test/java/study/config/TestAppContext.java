package study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailSender;
import study.user.service.DummyMailSender;
import study.user.service.UserService;
import study.user.service.UserServiceTest;

@Configuration
@Profile("test")
public class TestAppContext {

    @Bean
    public UserService testUserService() {
        return new UserServiceTest.TestUserService();
    }

    @Bean
    public MailSender mailSender() {
        return new DummyMailSender();
    }
}
