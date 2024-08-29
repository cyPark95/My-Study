package pcy.study.storeadmin.domain.storemenu.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pcy.study.db.storemenu.StoreMenu;
import pcy.study.db.storemenu.StoreMenuRepository;
import pcy.study.db.storemenu.enums.StoreMenuStatus;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreMenuService {

    private final StoreMenuRepository storeMenuRepository;

    public StoreMenu getStoreMenuWithThrow(Long id) {
        return Optional.ofNullable(storeMenuRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreMenuStatus.REGISTERED))
                .orElseThrow(() -> new IllegalArgumentException(new IllegalArgumentException(String.format("ID: [%d] StoreMenu Not Found", id))));
    }
}
