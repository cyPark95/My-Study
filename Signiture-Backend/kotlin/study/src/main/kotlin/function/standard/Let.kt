package pcy.study.function.standard

import java.time.LocalDateTime

fun main() {
    val now = LocalDateTime.now()
    val localDateTime: LocalDateTime? = null

    val result = now?.let { localDateTime: LocalDateTime ->
        // Let 함수 Scope
        println("let scope $localDateTime")
        "let scope"
    } ?: LocalDateTime.now()

    println("result: $result")

    UserDto(name = "홍길동").let {
        logic(it)
    }
}

fun logic(userDto: UserDto?): UserResponse? {
    return userDto?.let {
        UserEntity(name = it.name)
    }?.let {
        println(it)

        UserResponse(userName = it.name)
    }
}