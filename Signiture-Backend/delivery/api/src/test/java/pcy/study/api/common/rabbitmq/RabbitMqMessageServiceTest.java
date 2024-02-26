package pcy.study.api.common.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitMqMessageServiceTest {

    @Autowired
    private RabbitMqMessageService messageService;

    @Test
    void producer() {
        messageService.producer("Hello");
    }
}
