package pcy.study.storeadmin.domain.store.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pcy.study.db.store.Store;
import pcy.study.db.store.StoreRepository;
import pcy.study.db.store.enums.StoreStatus;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public Store getStoreWithThrow(Long id) {
        return Optional.ofNullable(storeRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreStatus.REGISTERED))
                .orElseThrow(() -> new IllegalArgumentException(String.format("ID: [%d] Store Not Found", id)));
    }

    public Store getStoreWithThrow(String name) {
        return Optional.ofNullable(storeRepository.findFirstByNameAndStatusOrderByIdDesc(name, StoreStatus.REGISTERED))
                .orElseThrow(() -> new IllegalArgumentException(String.format("Name: [%s] Store Not Found", name)));
    }
}
