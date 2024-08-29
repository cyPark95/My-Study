package pcy.study.db.storeuser

import org.springframework.data.jpa.repository.JpaRepository
import pcy.study.db.storeuser.enums.StoreUserStatus

interface StoreUserRepository : JpaRepository<StoreUser, Long> {

    fun findFirstByEmailAndStatusOrderByIdDesc(
        email: String?,
        status: StoreUserStatus?
    ): StoreUser?
}
