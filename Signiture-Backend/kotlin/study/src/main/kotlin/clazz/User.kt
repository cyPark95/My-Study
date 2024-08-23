package pcy.study.clazz

// Data Class는 기본적으로 Equals, HashCode, ToString을 재정의 해준다.
data class User(
    var name: String? = null,
    var age: Int? = 0,
    var email: String? = null
)