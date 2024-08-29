package pcy.study.db.store;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import pcy.study.db.BaseTimeEntity;
import pcy.study.db.store.enums.StoreCategory;
import pcy.study.db.store.enums.StoreStatus;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
public class Store extends BaseTimeEntity {

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 150, nullable = false)
    private String address;

    @Column(length = 50, nullable = false, columnDefinition = "VARCHAR")
    @Enumerated(EnumType.STRING)
    private StoreStatus status;

    @Column(length = 50, nullable = false, columnDefinition = "VARCHAR")
    @Enumerated(EnumType.STRING)
    private StoreCategory category;

    private double star;

    @Column(length = 200, nullable = false)
    private String thumbnailUrl;

    @Column(precision = 11, scale = 4, nullable = false)
    private BigDecimal minimumAmount;

    @Column(precision = 11, scale = 4, nullable = false)
    private BigDecimal minimumDeliveryAmount;

    @Column(length = 20)
    private String phoneNumber;

    @Builder
    private Store(String name, String address, StoreCategory category, String thumbnailUrl, BigDecimal minimumAmount, BigDecimal minimumDeliveryAmount, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.category = category;
        this.thumbnailUrl = thumbnailUrl;
        this.minimumAmount = minimumAmount;
        this.minimumDeliveryAmount = minimumDeliveryAmount;
        this.phoneNumber = phoneNumber;
        this.status = StoreStatus.REGISTERED;
    }
}
