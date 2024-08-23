package pcy.study.function.standard

fun main() {
    val result = UserDto().apply {
        this.name = "홍길동"
        println("User Name: $name")
    }

    result.myString()
}

fun UserDto.myString(): Unit {
    println("User Name: ${this.name}")
}