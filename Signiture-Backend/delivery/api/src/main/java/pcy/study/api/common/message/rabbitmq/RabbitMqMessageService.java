package pcy.study.api.common.message.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pcy.study.api.common.message.MessageService;

@Slf4j
@Component
public class RabbitMqMessageService implements MessageService {

    private final String exchange;
    private final String routeKey;

    private final RabbitTemplate rabbitTemplate;

    public RabbitMqMessageService(
            @Value("${rabbitmq.exchange}")
            String exchange,
            @Value("${rabbitmq.route-key}")
            String routeKey,
            RabbitTemplate rabbitTemplate
    ) {
        this.exchange = exchange;
        this.routeKey = routeKey;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void producer(Object object) {
        rabbitTemplate.convertAndSend(exchange, routeKey, object);
    }
}
