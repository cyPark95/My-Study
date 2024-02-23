package pcy.study.db.userordermenu;

import org.springframework.data.jpa.repository.JpaRepository;
import pcy.study.db.userordermenu.enums.UserOrderMenuStatus;

import java.util.List;

public interface UserOrderMenuRepository extends JpaRepository<UserOrderMenu, Long> {

    List<UserOrderMenu> findAllByUserOrderIdAndStatusOrderByIdDesc(Long userOrderId, UserOrderMenuStatus status);
}
