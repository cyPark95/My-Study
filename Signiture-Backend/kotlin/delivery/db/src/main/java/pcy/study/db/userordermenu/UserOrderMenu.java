package pcy.study.db.userordermenu;

import jakarta.persistence.*;
import lombok.*;
import pcy.study.db.BaseTimeEntity;
import pcy.study.db.storemenu.StoreMenu;
import pcy.study.db.userorder.UserOrder;
import pcy.study.db.userordermenu.enums.UserOrderMenuStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
public class UserOrderMenu extends BaseTimeEntity {

    @ManyToOne
    @JoinColumn(name = "userOrderId", nullable = false)
    @Column(nullable = false)
    private UserOrder userOrder;

    @ManyToOne
    @JoinColumn(name = "storeMenuId", nullable = false)
    @Column(nullable = false)
    private StoreMenu storeMenu;

    @Column(length = 50, nullable = false, columnDefinition = "VARCHAR")
    @Enumerated(EnumType.STRING)
    private UserOrderMenuStatus status;

    @Builder
    private UserOrderMenu(UserOrder userOrder, StoreMenu storeMenu) {
        this.userOrder = userOrder;
        this.storeMenu = storeMenu;
        this.status = UserOrderMenuStatus.REGISTERED;
    }
}
