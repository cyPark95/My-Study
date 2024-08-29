package pcy.study.db.store

import org.springframework.data.jpa.repository.JpaRepository
import pcy.study.db.store.enums.StoreCategory
import pcy.study.db.store.enums.StoreStatus

interface StoreRepository : JpaRepository<Store, Long> {

    fun findFirstByIdAndStatusOrderByIdDesc(
        id: Long?,
        status: StoreStatus?
    ): Store?

    fun findFirstByNameAndStatusOrderByIdDesc(
        name: String?,
        status: StoreStatus?
    ): Store?

    fun findAllByStatusOrderByIdDesc(status: StoreStatus?): List<Store>

    fun findAllByStatusAndCategoryOrderByStarDesc(
        status: StoreStatus?,
        category: StoreCategory?
    ): List<Store>
}
