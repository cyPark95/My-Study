package pcy.study.function.standard

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main() {
    val result = UserDto("").run {  // 지역 Scope
        name = "홍길동"
        "Empty"
    }

    println("result: $result")

    val now: LocalDateTime? = null
    val yesterday = now?.let {
        val minusDay = it.minusDays(1)
        minusDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
    } ?: run {
        val now = LocalDateTime.now()
        val minusDay = now.minusDays(1)
        minusDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
    }
    println("yesterday: $yesterday")
}