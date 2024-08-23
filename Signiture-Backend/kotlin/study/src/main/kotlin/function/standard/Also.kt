package pcy.study.function.standard

fun main() {
    val result = UserDto(
        name = "홍길동"
    ).also { dto: UserDto ->
        println(dto)

        dto.name = "Change"

        // Java: void = Kotlin: Unit
        UserDto(
            name = "임꺽정"
        )
    }

    println("result: $result")
}