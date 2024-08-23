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

    val user = User()
    user.name = "홍길동"
    user.email = "email@gmail.com"
    user.age = 20
    println(user)

    val copyUser = user.copy(name = "복제 사용자")
    println(copyUser)

    // Named Argument를 사용하면 매개변수를 직접 지정할 수 있다.
    User(
        age = 20,
        name = "홍길동",
        email = "email@gmail.com",  // 마지막 쉼표(,)에 대해 컴파일 에러가 발생하지 않는다.
    )
}
