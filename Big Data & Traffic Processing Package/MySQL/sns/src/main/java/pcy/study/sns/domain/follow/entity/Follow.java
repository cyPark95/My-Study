package pcy.study.sns.domain.follow.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.*;
import pcy.study.sns.domain.common.entity.BaseEntity;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "Follow_fromMemberId_toMemberId_uindex",
                        columnNames = {"fromMemberId", "toMemberId"}
                )
        }
)
public class Follow extends BaseEntity {

    @Column(nullable = false)
    private Long fromMemberId;

    @Column(nullable = false)
    private Long toMemberId;

    @Builder
    public Follow(Long fromMemberId, Long toMemberId) {
        this.fromMemberId = Objects.requireNonNull(fromMemberId);
        this.toMemberId = Objects.requireNonNull(toMemberId);
    }
}
