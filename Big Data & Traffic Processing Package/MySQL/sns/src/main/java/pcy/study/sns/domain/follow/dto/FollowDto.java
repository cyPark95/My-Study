package pcy.study.sns.domain.follow.dto;

public record FollowDto(
        Long id,
        Long fromMemberId,
        Long toMemberId
) {
}
