package pcy.study.function

import java.util.function.Predicate

fun main() {
    val list = listOf(
        "1",
        "2",
        "홍길동",
        "함수",
        "메서드"
    )

    val stringPredicate = object : Predicate<String> {
        override fun test(t: String): Boolean {
            return t == "?"
        }
    }

    list.filter { it == "?" }

    val add = { x: Int, y: Int -> x + y }
    println(add(3, 2))

    val higherOrderFunction = fun(x: Int, y: Int): Int {
        return x + y
    }
    println(higherOrderFunction(3, 2))

    lambda(5, 10, higherOrderFunction)
}

fun lambda(x: Int, y: Int, method: (Int, Int) -> Int) {
    println(method(x, y))
}
