package pcy.study.db.userordermenu.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserOrderMenuStatus {

    REGISTERED("등록"),
    UNREGISTERED("해지"),
    ;

    private final String description;
}
