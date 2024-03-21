package pcy.study.sns.domain.post.dto;

public record PostCommand(
        Long memberId,
        String contents
) {
}
