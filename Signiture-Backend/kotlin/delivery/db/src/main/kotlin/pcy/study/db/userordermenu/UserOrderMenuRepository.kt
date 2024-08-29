package pcy.study.db.userordermenu

import org.springframework.data.jpa.repository.JpaRepository
import pcy.study.db.userorder.UserOrder
import pcy.study.db.userordermenu.enums.UserOrderMenuStatus

interface UserOrderMenuRepository : JpaRepository<UserOrderMenu, Long> {

    fun findAllByUserOrderAndStatusOrderByIdDesc(
        userOrder: UserOrder?,
        status: UserOrderMenuStatus?
    ): List<UserOrderMenu>
}
