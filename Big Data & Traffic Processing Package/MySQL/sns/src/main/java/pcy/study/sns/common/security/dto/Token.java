package pcy.study.sns.common.security.dto;

public record Token(
        String token,
        Long tokenExpired
) {
}
