package pcy.study.sns.domain.post.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import pcy.study.sns.domain.post.dto.PostRegisterMessage;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostRegisterConsumer {

    private final TimelineWriteService timelineWriteService;

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void receiveMessage(PostRegisterMessage message) {
        timelineWriteService.deliveryToTimeline(message.postId(), message.memberIds());
    }
}
