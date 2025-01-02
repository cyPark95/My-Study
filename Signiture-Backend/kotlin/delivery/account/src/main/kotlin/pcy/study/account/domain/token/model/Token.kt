package pcy.study.account.domain.token.model

import java.time.LocalDateTime

data class Token(
    var token: String,
    var expiredAt: LocalDateTime
)
