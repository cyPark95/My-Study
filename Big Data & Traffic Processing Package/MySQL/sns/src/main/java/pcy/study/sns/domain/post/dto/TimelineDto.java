package pcy.study.sns.domain.post.dto;

public record TimelineDto(
        Long id,
        Long memberId,
        Long postId
) {
}
