package pcy.study.db.storeuser

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import pcy.study.db.storeuser.enums.StoreUserRole
import pcy.study.db.storeuser.enums.StoreUserStatus
import java.time.LocalDateTime

data class StoreUser @JvmOverloads constructor(

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @field:Column(nullable = false)
    var storeId: Long,

    @field:Column(length = 100, nullable = false)
    var email: String,

    @field:Column(length = 100, nullable = false)
    var password: String,

    @field:Column(length = 50, nullable = false, columnDefinition = "VARCHAR")
    @field:Enumerated(EnumType.STRING)
    var status: StoreUserStatus? = StoreUserStatus.REGISTERED,

    @field:Column(length = 50, nullable = false, columnDefinition = "VARCHAR")
    @field:Enumerated(EnumType.STRING)
    var role: StoreUserRole,

    @field:CreationTimestamp
    @field:Column(updatable = false)
    private var createdAt: LocalDateTime? = null,

    @field:UpdateTimestamp
    private var updatedAt: LocalDateTime? = null,
)
