package pcy.study.api.domain.userorder.producer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pcy.study.api.common.message.MessageService;
import pcy.study.common.message.model.UserOrderMessage;
import pcy.study.db.userorder.UserOrder;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static pcy.study.api.utility.UserOrderUtils.createUserOrderWithId;

@ExtendWith(MockitoExtension.class)
class UserOrderProducerTest {

    @InjectMocks
    private UserOrderProducer userOrderProducer;

    @Mock
    private MessageService messageService;

    @Test
    @DisplayName("주문 메시지 전송")
    void sendOrder() {
        // given
        UserOrder userOrder = createUserOrderWithId();
        UserOrderMessage userOrderMessage = UserOrderMessage.builder()
                .userOrderId(userOrder.getId())
                .build();

        // when
        userOrderProducer.sendOrder(userOrder);

        // then
        verify(messageService).producer(refEq(userOrderMessage));
    }
}
