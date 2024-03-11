package pcy.study.storeadmin.domain.userorder.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pcy.study.common.message.model.UserOrderMessage;
import pcy.study.storeadmin.domain.userorder.business.UserOrderBusiness;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserOrderConsumer {

    private final UserOrderBusiness userOrderBusiness;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void userOrderConsumer(UserOrderMessage userOrderMessage) {
        log.info("Consumer Message Queue UserOrder: {}", userOrderMessage);
        userOrderBusiness.pushUserOrder(userOrderMessage);
    }
}
