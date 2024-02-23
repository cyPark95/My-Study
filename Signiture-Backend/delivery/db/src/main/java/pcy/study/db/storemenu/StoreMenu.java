package pcy.study.db.storemenu;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pcy.study.db.BaseTimeEntity;
import pcy.study.db.storemenu.enums.StoreMenuStatus;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class StoreMenu extends BaseTimeEntity {

    @Column(nullable = false)
    private Long storeId;

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
    private StoreMenu(Long storeId, String name, BigDecimal amount, String thumbnailUrl) {
        this.storeId = storeId;
        this.name = name;
        this.amount = amount;
        this.thumbnailUrl = thumbnailUrl;
        this.status = StoreMenuStatus.REGISTERED;
    }
}
