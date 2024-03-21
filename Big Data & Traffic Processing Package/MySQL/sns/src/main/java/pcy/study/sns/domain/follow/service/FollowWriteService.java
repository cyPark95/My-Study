package pcy.study.sns.domain.follow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import pcy.study.sns.domain.follow.converter.FollowConverter;
import pcy.study.sns.domain.follow.dto.FollowDto;
import pcy.study.sns.domain.follow.repository.FollowRepository;
import pcy.study.sns.domain.member.dto.MemberDto;

import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowWriteService {

    private final FollowRepository followRepository;
    private final FollowConverter followConverter;

    public FollowDto register(MemberDto fromMember, MemberDto toMember) {
        Assert.isTrue(!Objects.equals(fromMember.id(), toMember.id()), "From, To 회원이 동일합니다.");

        /*
        from, to 회원 정보를 받아서 저장할 때,
        from <-> to validate
         */
        var follow = followConverter.toFollow(fromMember.id(), toMember.id());
        followRepository.save(follow);
        return followConverter.toDto(follow);
    }
}
