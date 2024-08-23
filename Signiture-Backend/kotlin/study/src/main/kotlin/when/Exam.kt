package pcy.study.`when`

fun main() {

    val result = when ("") {
        "" -> {
            ""
        }
        "MASTER", "ADMIN" -> {
            "master / admin"
        }
        "USER" -> {
            "user"
        }
        else -> {
            "default"
        }
    }

    val e = RuntimeException()
    when(e) {
        is NullPointerException -> {

        }
        is IllegalAccessException -> {
            // 자동으로 Casting 발생
        }
    }
}