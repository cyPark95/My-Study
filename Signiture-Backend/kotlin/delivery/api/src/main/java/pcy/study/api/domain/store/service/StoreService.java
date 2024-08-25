package pcy.study.api.domain.store.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pcy.study.api.common.api.error.ErrorCode;
import pcy.study.api.common.api.error.StoreErrorCode;
import pcy.study.api.common.exception.ApiException;
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
                .orElseThrow(() -> new ApiException("Store Entity is Null", ErrorCode.NULL_POINT));
    }

    public Store getStoreWithThrow(Long id) {
        return storeRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreStatus.REGISTERED)
                .orElseThrow(() -> new ApiException(
                        String.format("ID: [%d] Store Not Found", id),
                        StoreErrorCode.STORE_NOT_FOUND
                ));
    }

    public List<Store> searchByCategory(StoreCategory category) {
        return storeRepository.findAllByStatusAndCategoryOrderByStarDesc(StoreStatus.REGISTERED, category);
    }

    public List<Store> getAll() {
        return storeRepository.findAllByStatusOrderByIdDesc(StoreStatus.REGISTERED);
    }
}
