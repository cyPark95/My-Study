package pcy.study.storeadmin.domain.userorder.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pcy.study.db.userorder.UserOrder;
import pcy.study.db.userorder.UserOrderRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserOrderService {

    private final UserOrderRepository userOrderRepository;

    public UserOrder getUserOrderWithThrow(Long id) {
        return userOrderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("ID: [%d] UserOrder Not Found", id)));
    }
}
