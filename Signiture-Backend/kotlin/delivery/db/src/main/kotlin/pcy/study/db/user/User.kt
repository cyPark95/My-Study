package pcy.study.db.user

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import pcy.study.db.user.enums.UserStatus
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class User @JvmOverloads constructor(

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @field:Column(length = 50, nullable = false)
    var name: String,

    @field:Column(length = 100, nullable = false)
    var email: String,

    @field:Column(length = 100, nullable = false)
    var password: String,

    @field:Column(length = 50, nullable = false, columnDefinition = "VARCHAR")
    @field:Enumerated(EnumType.STRING)
    var status: UserStatus? = UserStatus.REGISTERED,

    @field:Column(length = 150, nullable = false)
    var address: String,

    @field:CreationTimestamp
    @field:Column(updatable = false)
    private var createdAt: LocalDateTime? = null,

    @field:UpdateTimestamp
    private var updatedAt: LocalDateTime? = null,
)
