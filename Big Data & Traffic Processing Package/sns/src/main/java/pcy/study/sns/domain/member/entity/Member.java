package pcy.study.sns.domain.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.util.Assert;
import pcy.study.sns.domain.common.entity.BaseEntity;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
public class Member extends BaseEntity {

    private static final Long NAME_MAX_LENGTH = 10L;

    @Column(length = 20, nullable = false)
    private String email;

    @Column(length = 20, nullable = false)
    private String nickname;

    @Column(nullable = false)
    private LocalDate birthday;

    @Builder
    public Member(String email, String nickname, LocalDate birthday) {
        validateNickname(nickname);
        this.email = Objects.requireNonNull(email);
        this.nickname = Objects.requireNonNull(nickname);
        this.birthday = Objects.requireNonNull(birthday);
    }

    public void changeNickname(String to) {
        Objects.requireNonNull(to);
        validateNickname(to);
        this.nickname = to;
    }

    private void validateNickname(String nickname) {
        Assert.isTrue(nickname.length() <= NAME_MAX_LENGTH, "최대 길이를 초과했습니다.");
    }
}
