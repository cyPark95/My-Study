package pcy.study.sns.domain.follow.converter;

import org.springframework.stereotype.Component;
import pcy.study.sns.domain.follow.dto.FollowDto;
import pcy.study.sns.domain.follow.entity.Follow;

import java.util.List;

@Component
public class FollowConverter {

    public List<FollowDto> toDto(List<Follow> follows) {
        return follows.stream()
                .map(this::toDto)
                .toList();
    }

    public FollowDto toDto(Follow follow) {
        return new FollowDto(
                follow.getId(),
                follow.getFromMemberId(),
                follow.getToMemberId()
        );
    }

    public Follow toFollow(Long fromMemberId, Long toMemberI) {
        return Follow.builder()
                .fromMemberId(fromMemberId)
                .toMemberId(toMemberI)
                .build();
    }
}
