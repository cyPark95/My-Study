package pcy.study.db.userorder;

import jakarta.persistence.*;
import lombok.*;
import pcy.study.db.BaseTimeEntity;
import pcy.study.db.store.Store;
import pcy.study.db.userorder.enums.UserOrderStatus;
import pcy.study.db.userordermenu.UserOrderMenu;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "userOrderMenus")
@Entity
public class UserOrder extends BaseTimeEntity {

    @Column(nullable = false)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "storeId", nullable = false)
    @Column(nullable = false)
    private Store store;

    @Column(length = 50, nullable = false, columnDefinition = "VARCHAR")
    @Enumerated(EnumType.STRING)
    private UserOrderStatus status;

    @Column(precision = 11, scale = 4, nullable = false)
    private BigDecimal amount;

    private LocalDateTime orderedAt;

    private LocalDateTime acceptedAt;

    private LocalDateTime cookingStartedAt;

    private LocalDateTime deliveryStartedAt;

    private LocalDateTime receivedAt;

    @OneToMany(mappedBy = "userOrder")
    private final List<UserOrderMenu> userOrderMenus = new ArrayList<>();

    @Builder
    private UserOrder(Long userId, Store store, BigDecimal amount) {
        this.userId = userId;
        this.store = store;
        this.amount = amount;
        this.status = UserOrderStatus.REGISTERED;
    }

    public void order() {
        this.status = UserOrderStatus.ORDER;
        this.orderedAt = LocalDateTime.now();
    }

    public void accept() {
        this.status = UserOrderStatus.ACCEPT;
        this.acceptedAt = LocalDateTime.now();
    }

    public void cooking() {
        this.status = UserOrderStatus.COOKING;
        this.cookingStartedAt = LocalDateTime.now();
    }

    public void delivery() {
        this.status = UserOrderStatus.DELIVERY;
        this.deliveryStartedAt = LocalDateTime.now();
    }

    public void receive() {
        this.status = UserOrderStatus.RECEIVE;
        this.receivedAt = LocalDateTime.now();
    }
}
