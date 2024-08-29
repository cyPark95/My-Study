package pcy.study.db.storemenu;

import jakarta.persistence.*;
import lombok.*;
import pcy.study.db.BaseTimeEntity;
import pcy.study.db.store.Store;
import pcy.study.db.storemenu.enums.StoreMenuStatus;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
public class StoreMenu extends BaseTimeEntity {

    @ManyToOne
    @JoinColumn(name = "storeId", nullable = false)
    @Column(nullable = false)
    private Store store;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(precision = 11, scale = 4, nullable = false)
    private BigDecimal amount;

    @Column(length = 50, nullable = false, columnDefinition = "VARCHAR")
    @Enumerated(EnumType.STRING)
    private StoreMenuStatus status;

    @Column(length = 200, nullable = false)
    private String thumbnailUrl;

    private int likeCount;
    private int sequence;

    @Builder
    private StoreMenu(Store store, String name, BigDecimal amount, String thumbnailUrl) {
        this.store = store;
        this.name = name;
        this.amount = amount;
        this.thumbnailUrl = thumbnailUrl;
        this.status = StoreMenuStatus.REGISTERED;
    }
}
