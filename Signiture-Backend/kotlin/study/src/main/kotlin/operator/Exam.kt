package pcy.study.operator

fun main() {
    val num = 10;
    callFunction(num)

    val nullNum: Int? = null
    callFunction(nullNum)

    callFunction()
}

fun callFunction(i: Int? = 100) {
    // Elvis 연산자
    // ? << null이 올 수도 있다.
    // 변수?. << 변수가 null이 아닐 때
    // 변수?: << 변수가 null 일 때
    i?.let {
        println(i)
    } ?: run {
        println("null  값 입니다.")
    }

//    val temp = if(i == null) "null 값 입니다." else i
    val temp = i?: "null 값 입니다."
    println(temp)
}