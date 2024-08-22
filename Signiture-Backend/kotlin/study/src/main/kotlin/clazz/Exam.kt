package pcy.study.clazz

interface Bark {
    fun bark()
}

abstract class Animal(
    private val name: String? = "Dog"
) : Bark {

    // body
    fun eat() {
        println("$name 식사 시작 합니다.")
    }
}

class Dog(
    val name: String? = null,
) : Animal(name) {

    var age: Int? = null

    override fun bark() {
        println("$name: 멍멍!!")
    }
}

fun main() {
    val dog = Dog("바둑이")
    dog.eat()
    dog.bark()

    dog.age = 10;
    println("age: ${dog.age}")
}
