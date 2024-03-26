package pcy.study.sns.domain.post.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Version;
import lombok.*;
import pcy.study.sns.domain.common.entity.BaseEntity;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
public class Post extends BaseEntity {

    @Column(nullable = false)
    private Long memberId;

    @Column(length = 100, nullable = false)
    private String contents;

    @Column(nullable = false)
    private LocalDate createdDate;

    private Long likeCount;

    @Version
    private Integer version;

    @Builder
    public Post(Long memberId, String contents, LocalDate createdDate, Long likeCount) {
        this.memberId = Objects.requireNonNull(memberId);
        this.contents = Objects.requireNonNull(contents);

        this.createdDate = createdDate == null ? LocalDate.now() : createdDate;
        this.likeCount = likeCount == null ? 0 : likeCount;
    }

    public void incrementLickCount() {
        likeCount ++;
    }
}
