package pcy.study.storeadmin.domain.userorder.consumer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pcy.study.common.message.model.UserOrderMessage;
import pcy.study.storeadmin.domain.userorder.business.UserOrderBusiness;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static pcy.study.storeadmin.utility.UserOrderUtils.USER_ORDER_ID;

@SpringBootTest
class UserOrderConsumerTest {

    @MockBean
    private UserOrderBusiness userOrderBusiness;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.queue}")
    private String queueName;

    @Test
    @DisplayName("주문 메시지 수신")
    void userOrderConsumer() {
        // given
        UserOrderMessage userOrderMessage = UserOrderMessage.builder()
                .userOrderId(USER_ORDER_ID)
                .build();

        // when
        rabbitTemplate.convertAndSend(queueName, userOrderMessage);

        // then
        await()
                .atMost(500, TimeUnit.MILLISECONDS)
                .untilAsserted(() -> verify(userOrderBusiness).pushUserOrder(any(UserOrderMessage.class)));
    }
}
