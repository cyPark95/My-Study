package pcy.study.sns.domain.post.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import pcy.study.sns.domain.common.entity.BaseEntity;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
@Entity
public class Timeline extends BaseEntity {

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private Long postId;

    @Builder
    public Timeline(Long memberId, Long postId) {
        this.memberId = Objects.requireNonNull(memberId);
        this.postId = Objects.requireNonNull(postId);
    }
}
