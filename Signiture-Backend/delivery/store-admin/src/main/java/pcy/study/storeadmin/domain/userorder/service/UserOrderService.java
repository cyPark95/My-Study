package pcy.study.storeadmin.domain.userorder.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pcy.study.db.userorder.UserOrder;
import pcy.study.db.userorder.UserOrderRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserOrderService {

    private final UserOrderRepository userOrderRepository;

    public Optional<UserOrder> getUserOrder(Long id) {
        return userOrderRepository.findById(id);
    }
}
