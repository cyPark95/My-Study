package pcy.study.db.store

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import pcy.study.db.store.enums.StoreCategory
import pcy.study.db.store.enums.StoreStatus
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
data class Store @JvmOverloads constructor(

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,


    @field:Column(length = 100, nullable = false)
    var name: String,

    @field:Column(length = 150, nullable = false)
    val address: String,

    @field:Column(length = 50, nullable = false, columnDefinition = "VARCHAR")
    @field:Enumerated(EnumType.STRING)
    var category: StoreCategory,

    var star: Double? = 0.0,

    @field:Column(length = 200, nullable = false)
    var thumbnailUrl: String,

    @field:Column(precision = 11, scale = 4, nullable = false)
    var minimumAmount: BigDecimal,

    @field:Column(precision = 11, scale = 4, nullable = false)
    var minimumDeliveryAmount: BigDecimal,

    @field:Column(length = 20)
    var phoneNumber: String,

    @field:Column(length = 50, nullable = false, columnDefinition = "VARCHAR")
    @field:Enumerated(EnumType.STRING)
    var status: StoreStatus? = StoreStatus.REGISTERED,

    @field:CreationTimestamp
    @field:Column(updatable = false)
    private var createdAt: LocalDateTime? = null,

    @field:UpdateTimestamp
    private var updatedAt: LocalDateTime? = null,
)