package pcy.study.value

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main() {
    Exam(Store())
    println(DateTimeUtil().localDateTimeToString())
}

class Exam(store: Store) {

    // 초기화 시에 호출되는 Scope
    init {
        val storeRegisterAt = toLocalDateTimeString(store.registerAt)
        println(storeRegisterAt)
    }

    private fun toLocalDateTimeString(localDateTime: LocalDateTime?): String {
        val temp = localDateTime ?: LocalDateTime.now()
        return temp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
    }
}

data class Store(
    var registerAt: LocalDateTime? = null
)