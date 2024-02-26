package pcy.study.storeadmin.domain.store.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pcy.study.db.store.Store;
import pcy.study.db.store.StoreRepository;
import pcy.study.db.store.enums.StoreStatus;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public Store getStoreWithThrow(String name) {
        return storeRepository.findFirstByNameAndStatusOrderByIdDesc(name, StoreStatus.REGISTERED)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Name: [%s] Store Not Found", name)));
    }
}
