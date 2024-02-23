package pcy.study.db.userordermenu;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pcy.study.db.BaseTimeEntity;
import pcy.study.db.userordermenu.enums.UserOrderMenuStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserOrderMenu extends BaseTimeEntity {

    @Column(nullable = false)
    private Long userOrderId;

    @Column(nullable = false)
    private Long storeMenuId;

    @Column(length = 50, nullable = false, columnDefinition = "VARCHAR")
    @Enumerated(EnumType.STRING)
    private UserOrderMenuStatus status;

    @Builder
    private UserOrderMenu(Long userOrderId, Long storeMenuId) {
        this.userOrderId = userOrderId;
        this.storeMenuId = storeMenuId;
        this.status = UserOrderMenuStatus.REGISTERED;
    }
}
