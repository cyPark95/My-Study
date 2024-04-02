package pcy.study.sns.domain.post.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import pcy.study.sns.common.IntegrationTest;
import pcy.study.sns.domain.post.dto.PostRegisterMessage;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.verify;

@IntegrationTest
class PostRegisterProducerTest {

    @Autowired
    private PostRegisterProducer postRegisterProducer;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Test
    @DisplayName("게시글 등록 메시지 전송")
    void sendMessage() {
        // when
        postRegisterProducer.sendMessage(-1L, List.of(0L, -1L));

        // then
        verify(rabbitTemplate, atMostOnce()).convertAndSend(anyString(), anyString(), any(PostRegisterMessage.class));
    }
}
