package pcy.study.sns.domain.member.entity;

import jakarta.persistence.Entity;
import lombok.*;
import pcy.study.sns.domain.common.entity.BaseEntity;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@ToString
public class MemberNicknameHistory extends BaseEntity {

    private Long memberId;

    private String nickname;

    private LocalDateTime createdAt;

    @Builder
    public MemberNicknameHistory(Long memberId, String nickname, LocalDateTime createdAt) {
        this.memberId = Objects.requireNonNull(memberId);
        this.nickname = Objects.requireNonNull(nickname);
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }
}
