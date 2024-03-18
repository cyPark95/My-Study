package pcy.study.sns.domain.follow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pcy.study.sns.domain.follow.converter.FollowConverter;
import pcy.study.sns.domain.follow.dto.FollowDto;
import pcy.study.sns.domain.follow.entity.Follow;
import pcy.study.sns.domain.follow.repository.FollowRepository;
import pcy.study.sns.domain.member.dto.MemberDto;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FollowWriteService {

    private final FollowRepository followRepository;
    private final FollowConverter followConverter;

    public FollowDto register(MemberDto fromMember, MemberDto toMember) {
        /*
        from, to 회원 정보를 받아서 저장할 때,
        from <-> to validate
         */
        Assert.isTrue(!Objects.equals(fromMember.id(), toMember.id()), "From, To 회원이 동일합니다.");

        var follow = Follow.builder()
                .fromMemberId(fromMember.id())
                .toMemberId(toMember.id())
                .build();

        var newFollow = followRepository.save(follow);
        return followConverter.toDto(newFollow);
    }
}