package pcy.study.db.userorder

import org.springframework.data.jpa.repository.JpaRepository
import pcy.study.db.userorder.enums.UserOrderStatus

interface UserOrderRepository : JpaRepository<UserOrder, Long> {

    fun findAllByUserIdAndStatusOrderByIdDesc(
        userId: Long?,
        status: UserOrderStatus?
    ): List<UserOrder>

    fun findAllByUserIdAndStatusInOrderByIdDesc(
        userId: Long?,
        status: List<UserOrderStatus>
    ): List<UserOrder>

    fun findFirstByIdAndUserId(
        id: Long?,
        userId: Long?
    ): UserOrder?
}
