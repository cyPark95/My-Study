package pcy.study.sns.domain.member.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Member {

    private static final Long NAME_MAX_LENGTH = 10L;

    private final Long id;

    private final String email;

    private String nickname;

    private final LocalDate birthday;

    private final LocalDateTime createdAt;

    @Builder
    public Member(Long id, String email, String nickname, LocalDate birthday, LocalDateTime createdAt) {
        this.id = id;

        validateNickname(nickname);
        this.email = Objects.requireNonNull(email);
        this.nickname = Objects.requireNonNull(nickname);
        this.birthday = Objects.requireNonNull(birthday);

        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
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
