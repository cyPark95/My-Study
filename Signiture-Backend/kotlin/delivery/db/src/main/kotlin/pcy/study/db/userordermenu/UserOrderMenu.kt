package pcy.study.db.userordermenu

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import pcy.study.db.storemenu.StoreMenu
import pcy.study.db.userorder.UserOrder
import pcy.study.db.userordermenu.enums.UserOrderMenuStatus
import java.time.LocalDateTime

class UserOrderMenu @JvmOverloads constructor(

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @field:ManyToOne
    @field:JoinColumn(name = "user_order_id", nullable = false)
    var userOrder: UserOrder,

    @field:ManyToOne
    @field:JoinColumn(name = "store_menu_id", nullable = false)
    var storeMenu: StoreMenu,

    @field:Column(length = 50, nullable = false, columnDefinition = "VARCHAR")
    @field:Enumerated(EnumType.STRING)
    var status: UserOrderMenuStatus? = UserOrderMenuStatus.REGISTERED,

    @field:CreationTimestamp
    @field:Column(updatable = false)
    private var createdAt: LocalDateTime? = null,

    @field:UpdateTimestamp
    private var updatedAt: LocalDateTime? = null,
) {
}