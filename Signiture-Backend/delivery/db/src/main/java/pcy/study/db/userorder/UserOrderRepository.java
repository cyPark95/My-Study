package pcy.study.db.userorder;

import org.springframework.data.jpa.repository.JpaRepository;
import pcy.study.db.userorder.enums.UserOrderStatus;

import java.util.List;
import java.util.Optional;

public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {

    List<UserOrder> findAllByUserIdAndStatusOrderByIdDesc(Long userId, UserOrderStatus status);

    List<UserOrder> findAllByUserIdAndStatusInOrderByIdDesc(Long userId, List<UserOrderStatus> status);

    Optional<UserOrder> findFirstByIdAndUserIdAndStatus(Long id, Long userId, UserOrderStatus status);
}
