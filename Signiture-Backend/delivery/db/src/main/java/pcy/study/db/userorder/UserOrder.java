package pcy.study.db.userorder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pcy.study.db.BaseTimeEntity;
import pcy.study.db.userorder.enums.UserOrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserOrder extends BaseTimeEntity {

    @Column(nullable = false)
    private Long userId;

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

    @Builder
    private UserOrder(Long userId, BigDecimal amount) {
        this.userId = userId;
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
