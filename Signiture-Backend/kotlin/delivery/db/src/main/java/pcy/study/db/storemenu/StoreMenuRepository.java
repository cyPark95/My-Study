package pcy.study.db.storemenu;

import org.springframework.data.jpa.repository.JpaRepository;
import pcy.study.db.storemenu.enums.StoreMenuStatus;

import java.util.List;
import java.util.Optional;

public interface StoreMenuRepository extends JpaRepository<StoreMenu, Long> {

    Optional<StoreMenu> findFirstByIdAndStatusOrderByIdDesc(Long id, StoreMenuStatus status);

    List<StoreMenu> findAllByStoreIdAndStatusOrderBySequenceDesc(Long storeId, StoreMenuStatus status);
}
