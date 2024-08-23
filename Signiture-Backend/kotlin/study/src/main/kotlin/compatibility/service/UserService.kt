package pcy.study.compatibility.service

import compatibility.model.JavaUser
import java.time.LocalDateTime

class UserService {

    fun createJavaUser(javaUser: JavaUser? = null) {
        val user = JavaUser(
            "홍길동",
            20,
            javaUser?.email,
            javaUser?.phoneNumber,
            LocalDateTime.now(),
        )

        user.name?.let {
            println(it)
        } ?: println("Is Null")
    }
}

fun main() {
    val javaUser = JavaUser(
        "Dummy",
        0,
        "email@gmail.com",
        "000-0000-0000",
        LocalDateTime.of(2000, 8, 23, 16, 25, 30),
    )

    UserService().createJavaUser(javaUser)
}