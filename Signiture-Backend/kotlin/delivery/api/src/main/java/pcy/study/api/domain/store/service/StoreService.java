package pcy.study.api.domain.store.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pcy.study.common.api.code.ErrorCode;
import pcy.study.common.api.code.StoreErrorCode;
import pcy.study.common.exception.ApiException;
import pcy.study.db.store.Store;
import pcy.study.db.store.StoreRepository;
import pcy.study.db.store.enums.StoreCategory;
import pcy.study.db.store.enums.StoreStatus;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public Store register(Store store) {
        return Optional.ofNullable(store)
                .map(storeRepository::save)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "Store Entity is Null"));
    }

    public Store getStoreWithThrow(Long id) {
        return Optional.ofNullable(storeRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreStatus.REGISTERED))
                .orElseThrow(() -> new ApiException(
                        StoreErrorCode.STORE_NOT_FOUND,
                        String.format("ID: [%d] Store Not Found", id)
                ));
    }

    public List<Store> searchByCategory(StoreCategory category) {
        return storeRepository.findAllByStatusAndCategoryOrderByStarDesc(StoreStatus.REGISTERED, category);
    }

    public List<Store> getAll() {
        return storeRepository.findAllByStatusOrderByIdDesc(StoreStatus.REGISTERED);
    }
}
