package pcy.study.storeadmin.domain.storemenu.converter;

import org.springframework.stereotype.Service;
import pcy.study.db.storemenu.StoreMenu;
import pcy.study.storeadmin.domain.storemenu.controller.model.StoreMenuResponse;

import java.util.List;
import java.util.Optional;

@Service
public class StoreMenuConverter {

    public List<StoreMenuResponse> toResponse(List<StoreMenu> storeMenus) {
        return storeMenus.stream()
                .map(this::toResponse)
                .toList();
    }

    public StoreMenuResponse toResponse(StoreMenu storeMenu) {
        return Optional.ofNullable(storeMenu)
                .map(it -> StoreMenuResponse.builder()
                        .id(storeMenu.getId())
                        .name(storeMenu.getName())
                        .amount(storeMenu.getAmount())
                        .thumbnailUrl(storeMenu.getThumbnailUrl())
                        .build())
                .orElseThrow(() -> new IllegalArgumentException("StoreMenu Entity is Null"));
    }
}
