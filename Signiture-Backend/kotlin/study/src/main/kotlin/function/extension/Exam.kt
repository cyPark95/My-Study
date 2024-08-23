package pcy.study.function.extension

fun main() {
    val nullUser = ExamUser()
    exam(nullUser)

    val user = ExamUser("name")
    exam(user)
}

fun exam(examUser: ExamUser?) {

    if(examUser.isNotNull() && examUser?.name.isNotNullOrBlank()) {
        println("Exam Name: ${examUser?.name}")
    }
}

data class ExamUser(var name: String? = null)

fun String?.isNotNullOrBlank(): Boolean {  // 확장 함수 extension function
    return !this.isNullOrBlank()
}

fun Any?.isNotNull(): Boolean {
    return this != null
}