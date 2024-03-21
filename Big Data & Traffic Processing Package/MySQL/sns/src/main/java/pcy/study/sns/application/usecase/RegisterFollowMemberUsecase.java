package pcy.study.sns.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pcy.study.sns.domain.follow.service.FollowWriteService;
import pcy.study.sns.domain.member.service.MemberReadService;

/**
 * Usecase Layer
 * - 도메인 서비스의 흐름을 제어하는 역할
 * - 각각의 비즈니스 로직은 도메인 서비스에서 구현되어야 한다.
 */
@Service
@RequiredArgsConstructor
public class RegisterFollowMemberUsecase {

    private final MemberReadService memberReadService;
    private final FollowWriteService followWriteService;

    public void execute(Long fromMemberId, Long toMemberId) {
        /*
        1. 입력받은 memberId로 회원 조회
        2. FollowWriteService.create()
         */
        var fromMember = memberReadService.getMember(fromMemberId);
        var toMember = memberReadService.getMember(toMemberId);

        followWriteService.register(fromMember, toMember);
    }
}
