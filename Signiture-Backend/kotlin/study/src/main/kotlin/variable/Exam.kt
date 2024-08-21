package pcy.study.variable

fun main() {
    // var: mutable(가변)
    // val(final): immutable(불변)

    // : [타입]
    val name: String = "홍길동"

    // Kotlin은 모두 Reference 타입
    val age: Int = 10

    println(name + "(" + age + ")")
    val result = "사용자 이름은: $name"
    println(result)
}