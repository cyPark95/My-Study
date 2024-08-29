package pcy.study.api.domain.storemenu.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pcy.study.common.api.code.ErrorCode;
import pcy.study.common.api.code.StoreMenuErrorCode;
import pcy.study.common.exception.ApiException;
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
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "StoreMenu Entity is Null"));
    }

    public StoreMenu getStoreMenuWithThrow(Long id) {
        return Optional.ofNullable(storeMenuRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreMenuStatus.REGISTERED))
                .orElseThrow(() -> new ApiException(
                        StoreMenuErrorCode.STORE_MENU_NOT_FOUND,
                        String.format("ID: [%d] StoreMenu Not Found", id)
                ));
    }

    public List<StoreMenu> searchByStoreId(Long storeId) {
        return storeMenuRepository.findAllByStoreIdAndStatusOrderBySequenceDesc(storeId, StoreMenuStatus.REGISTERED);
    }
}
