package pcy.study.sns.domain.post.dto;

import java.util.List;

public record PostRegisterMessage(
        Long postId,
        List<Long> memberIds
) {
}
