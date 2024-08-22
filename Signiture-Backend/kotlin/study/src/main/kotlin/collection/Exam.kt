package pcy.study.collection

fun main() {
    list()
}

private fun list() {
    // mutable(가변) var, immutable(불변) val 명확하게 구분
    val mutableUsers = mutableListOf<User>()
    mutableUsers.add(User("1", 10))
    mutableUsers.add(User("2", 20))
    mutableUsers.add(User("3", 30))

    val immutableUsers = listOf(
        User("4", 40),
        User("5", 50)
    )
    // immutableUsers.add()를 지원하지 않는다.

    for (element in immutableUsers) {
        println(element)
    }

    immutableUsers.forEach { println(it) }

    immutableUsers.forEachIndexed { index, user -> println("index: $index / user: $user") }
}

private fun map() {
    // java Object == Any
    val immutableMap = mapOf<String, Any>(
        Pair("name", "name"),
        "age" to 20
    )

    val mutableMap = mutableMapOf(
        "key" to "value",
    )

    mutableMap["key"] = "mutable"
    val value = mutableMap["key"]
    println(value)

    var triple = Triple<String, String, String>(
        first = "name",
        second = "age",
        third = "value"
    )
}

class User(
    var name: String,
    var age: Int
)