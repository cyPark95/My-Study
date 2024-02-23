package pcy.study.api.domain.storemenu.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pcy.study.api.common.api.error.ErrorCode;
import pcy.study.api.common.api.error.StoreErrorCode;
import pcy.study.api.common.exception.ApiException;
import pcy.study.db.storemenu.StoreMenu;
import pcy.study.db.storemenu.StoreMenuRepository;
import pcy.study.db.storemenu.enums.StoreMenuStatus;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreMenuService {

    private final StoreMenuRepository storeMenuRepository;

    public StoreMenu register(StoreMenu storeMenu) {
        return Optional.ofNullable(storeMenu)
                .map(storeMenuRepository::save)
                .orElseThrow(() -> new ApiException("StoreMenu Entity is Null", ErrorCode.NULL_POINT));
    }

    public StoreMenu getStoreMenuWithThrow(Long id) {
        return storeMenuRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreMenuStatus.REGISTERED)
                .orElseThrow(() -> new ApiException(
                        String.format("ID: [%d] StoreMenu Not Found", id),
                        StoreErrorCode.STORE_NOT_FOUND
                ));
    }

    public List<StoreMenu> searchByStoreId(Long storeId) {
        return storeMenuRepository.findAllByStoreIdAndStatusOrderBySequenceDesc(storeId, StoreMenuStatus.REGISTERED);
    }
}
