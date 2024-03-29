package pcy.study.sns.domain.post.dto;

import java.time.LocalDate;

public record PostDto(
        Long id,
        Long memberId,
        String contents,
        LocalDate createdDate,
        Long likeCount
) {
}
