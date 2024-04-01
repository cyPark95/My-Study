package pcy.study.sns.domain.post.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pcy.study.sns.domain.post.dto.PostRegisterMessage;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostRegisterProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(Long postId, List<Long> memberIds) {
        var message = new PostRegisterMessage(postId, memberIds);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }
}
