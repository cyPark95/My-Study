package pcy.study.sns.domain.follow.converter;

import org.springframework.stereotype.Component;
import pcy.study.sns.domain.follow.dto.FollowDto;
import pcy.study.sns.domain.follow.entity.Follow;

@Component
public class FollowConverter {

    public FollowDto toDto(Follow follow) {
        return new FollowDto(
                follow.getId(),
                follow.getFromMemberId(),
                follow.getToMemberId()
        );
    }
}
