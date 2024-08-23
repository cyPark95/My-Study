package pcy.study.value

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateTimeUtil {

    private val KST_PATTERN = "yyyy년 MM월 dd일 HH시 mm분 ss초"

    fun localDateTimeToString(
        localDateTime: LocalDateTime = LocalDateTime.now(),
        pattern: String = KST_PATTERN
    ): String {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern))
    }
}