package pcy.study.db.storemenu

import org.springframework.data.jpa.repository.JpaRepository
import pcy.study.db.storemenu.enums.StoreMenuStatus

interface StoreMenuRepository : JpaRepository<StoreMenu, Long> {

    fun findFirstByIdAndStatusOrderByIdDesc(
        id: Long?,
        status: StoreMenuStatus?
    ): StoreMenu?

    fun findAllByStoreIdAndStatusOrderBySequenceDesc(
        storeId: Long?,
        status: StoreMenuStatus?
    ): List<StoreMenu>
}
