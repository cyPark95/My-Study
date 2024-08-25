package pcy.study.db.storeuser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pcy.study.db.BaseTimeEntity;
import pcy.study.db.storeuser.enums.StoreUserRole;
import pcy.study.db.storeuser.enums.StoreUserStatus;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class StoreUser extends BaseTimeEntity {

    @Column(nullable = false)
    private Long storeId;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 50, nullable = false, columnDefinition = "VARCHAR")
    @Enumerated(EnumType.STRING)
    private StoreUserStatus status;

    @Column(length = 50, nullable = false, columnDefinition = "VARCHAR")
    @Enumerated(EnumType.STRING)
    private StoreUserRole role;

    private LocalDateTime unregisteredAt;
    private LocalDateTime lastLoginAt;

    @Builder
    private StoreUser(Long storeId, String email, String password, StoreUserRole role) {
        this.storeId = storeId;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = StoreUserStatus.REGISTERED;
    }
}
