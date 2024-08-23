package pcy.study.compatibility.model

import java.time.LocalDateTime

data class KotlinUser(
    val name: String? = null,
    val age: Int? = null,
    val email: String? = null,
    val phoneNumber: String? = null,
    val registeredAt: LocalDateTime? = null,
)