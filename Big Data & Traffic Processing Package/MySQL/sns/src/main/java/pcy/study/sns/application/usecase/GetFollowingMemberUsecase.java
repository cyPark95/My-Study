package pcy.study.sns.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pcy.study.sns.domain.follow.dto.FollowDto;
import pcy.study.sns.domain.follow.service.FollowReadService;
import pcy.study.sns.domain.member.dto.MemberDto;
import pcy.study.sns.domain.member.service.MemberReadService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetFollowingMemberUsecase {

    private final MemberReadService memberReadService;
    private final FollowReadService followReadService;

    public List<MemberDto> execute(Long fromMemberId) {
        /*
        1. fromMemberId = memberId -> Follow List
        2. 1번을 순회하면서 회원 정보를 조회
         */
        var followings = followReadService.getFollowings(fromMemberId);
        var followingMemberIds = followings.stream()
                .map(FollowDto::toMemberId)
                .toList();
        return memberReadService.getMembers(followingMemberIds);
    }
}
