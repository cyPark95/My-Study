package pcy.study.sns.domain.post.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import pcy.study.sns.common.IntegrationTest;
import pcy.study.sns.common.TestContainerTest;
import pcy.study.sns.domain.post.dto.PostRegisterMessage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

@IntegrationTest
class PostRegisterConsumerTest extends TestContainerTest {

    @MockBean
    private TimelineWriteService timelineWriteService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Test
    @DisplayName("게시글 등록 메시지 수신")
    void receiveMessage() {
        // given
        var message = new PostRegisterMessage(-1L, List.of(0L, -1L));

        // when
        rabbitTemplate.convertAndSend(queueName, message);

        // then
        await()
                .atMost(5, TimeUnit.SECONDS)
                .untilAsserted(() -> verify(timelineWriteService).deliveryToTimeline(anyLong(), anyList()));
    }
}
