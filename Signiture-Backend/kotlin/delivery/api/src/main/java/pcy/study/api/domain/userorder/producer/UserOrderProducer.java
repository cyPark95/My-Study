package pcy.study.api.domain.userorder.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pcy.study.api.common.message.MessageService;
import pcy.study.common.message.model.UserOrderMessage;
import pcy.study.db.userorder.UserOrder;

@Service
@RequiredArgsConstructor
public class UserOrderProducer {

    private final MessageService messageService;

    public void sendOrder(UserOrder userOrder) {
        sendOrder(userOrder.getId());
    }

    private void sendOrder(Long id) {
        var message = UserOrderMessage.builder()
                .userOrderId(id)
                .build();

        messageService.producer(message);
    }
}
