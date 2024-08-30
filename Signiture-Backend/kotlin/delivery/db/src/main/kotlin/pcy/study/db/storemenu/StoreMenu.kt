package pcy.study.db.storemenu

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import pcy.study.db.store.Store
import pcy.study.db.storemenu.enums.StoreMenuStatus
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
data class StoreMenu @JvmOverloads constructor(

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @field:ManyToOne
    @field:JoinColumn(name = "store_id", nullable = false)
    var store: Store,

    @field:Column(length = 100, nullable = false)
    var name: String,

    @field:Column(precision = 11, scale = 4, nullable = false)
    var amount: BigDecimal,

    @field:Column(length = 50, nullable = false, columnDefinition = "VARCHAR")
    @field:Enumerated(EnumType.STRING)
    var status: StoreMenuStatus? = StoreMenuStatus.REGISTERED,

    @field:Column(length = 200, nullable = false)
    var thumbnailUrl: String,

    var likeCount: Int? = 0,
    var sequence: Int? = 0,

    @field:CreationTimestamp
    @field:Column(updatable = false)
    private var createdAt: LocalDateTime? = null,

    @field:UpdateTimestamp
    private var updatedAt: LocalDateTime? = null,
)