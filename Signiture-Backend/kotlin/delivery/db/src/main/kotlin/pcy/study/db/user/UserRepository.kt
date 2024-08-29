package pcy.study.db.user

import org.springframework.data.jpa.repository.JpaRepository
import pcy.study.db.user.enums.UserStatus

interface UserRepository: JpaRepository<User, Long> {

    fun findFirstByIdAndStatusOrderByIdDesc(
        id: Long?,
        userStatus: UserStatus?
    ): User?

    fun findFirstByEmailAndPasswordAndStatusOrderByIdDesc(
        email: String?,
        password: String?,
        status: UserStatus?
    ): User?
}