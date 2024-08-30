package pcy.study.db.userorder

import jakarta.persistence.*
import lombok.ToString
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import pcy.study.db.store.Store
import pcy.study.db.userorder.enums.UserOrderStatus
import pcy.study.db.userordermenu.UserOrderMenu
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@ToString(exclude = ["userOrderMenus"])
class UserOrder @JvmOverloads constructor(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @field:Column(nullable = false)
    var userId: Long,

    @field:ManyToOne
    @field:JoinColumn(name = "store_id", nullable = false)
    var store: Store,

    @field:Column(length = 50, nullable = false, columnDefinition = "VARCHAR")
    @field:Enumerated(EnumType.STRING)
    var status: UserOrderStatus? = UserOrderStatus.REGISTERED,

    @field:Column(precision = 11, scale = 4, nullable = false)
    var amount: BigDecimal,

    var orderedAt: LocalDateTime? = null,
    var acceptedAt: LocalDateTime? = null,
    var cookingStartedAt: LocalDateTime? = null,
    var deliveryStartedAt: LocalDateTime? = null,
    var receivedAt: LocalDateTime? = null,

    @field:OneToMany(mappedBy = "userOrder")
    var userOrderMenus: MutableList<UserOrderMenu>? = mutableListOf(),

    @field:CreationTimestamp
    @field:Column(updatable = false)
    private var createdAt: LocalDateTime? = null,

    @field:UpdateTimestamp
    private var updatedAt: LocalDateTime? = null,
) {

    fun order() {
        this.status = UserOrderStatus.ORDER
        this.orderedAt = LocalDateTime.now()
    }

    fun accept() {
        this.status = UserOrderStatus.ACCEPT
        this.acceptedAt = LocalDateTime.now()
    }

    fun cooking() {
        this.status = UserOrderStatus.COOKING
        this.cookingStartedAt = LocalDateTime.now()
    }

    fun delivery() {
        this.status = UserOrderStatus.DELIVERY
        this.deliveryStartedAt = LocalDateTime.now()
    }

    fun receive() {
        this.status = UserOrderStatus.RECEIVE
        this.receivedAt = LocalDateTime.now()
    }
}