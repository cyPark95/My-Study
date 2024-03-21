package pcy.study.sns.domain.member.dto;

import java.time.LocalDate;

/**
 * record
 * Java 14부터 프리뷰로 등록되었고, 16부터 정식 등록됐다.
 * Getter, Setter 자동 생성 및 프로퍼티 형식으로 사용
 */
public record MemberRegisterCommand(
        String email,
        String nickname,
        LocalDate birthday
) {
}
