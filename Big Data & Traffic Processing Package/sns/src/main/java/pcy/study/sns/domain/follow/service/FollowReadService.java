package pcy.study.sns.domain.follow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pcy.study.sns.domain.follow.converter.FollowConverter;
import pcy.study.sns.domain.follow.dto.FollowDto;
import pcy.study.sns.domain.follow.repository.FollowRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowReadService {

    private final FollowRepository followRepository;
    private final FollowConverter followConverter;

    public List<FollowDto> getFollowings(Long fromMemberId) {
        var followings = followRepository.findAllByFromMemberId(fromMemberId);
        return followings.stream()
                .map(followConverter::toDto)
                .toList();
    }
}
