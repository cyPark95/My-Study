package pcy.study.db.store;

import org.springframework.data.jpa.repository.JpaRepository;
import pcy.study.db.store.enums.StoreCategory;
import pcy.study.db.store.enums.StoreStatus;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Optional<Store> findFirstByIdAndStatusOrderByIdDesc(Long id, StoreStatus status);

    List<Store> findAllByStatusOrderByIdDesc(StoreStatus status);

    List<Store> findAllByStatusAndCategoryOrderByStarDesc(StoreStatus status, StoreCategory category);
}
