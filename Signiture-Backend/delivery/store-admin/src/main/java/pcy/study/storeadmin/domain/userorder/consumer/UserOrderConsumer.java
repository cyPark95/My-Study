package pcy.study.storeadmin.domain.userorder.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pcy.study.common.message.model.UserOrderMessage;

@Slf4j
@Component
public class UserOrderConsumer {

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void UserOrderConsumer(UserOrderMessage userOrderMessage) {
        log.info("Consumer Message Queue UserOrder ID: {}", userOrderMessage);
    }
}
