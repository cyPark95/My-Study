package pcy.study.sns.application.usecase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pcy.study.sns.common.IntegrationTest;
import pcy.study.sns.domain.follow.repository.FollowRepository;
import pcy.study.sns.util.StepUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

@IntegrationTest
@DisplayName("회원을 팔로우한다.")
class RegisterFollowMemberUsecaseTest {

    @Autowired
    private RegisterFollowMemberUsecase registerFollowMemberUsecase;

    @Autowired
    private StepUtil stepUtil;

    @Autowired
    private FollowRepository followRepository;

    @Test
    void execute() {
        // given
        var fromMember = stepUtil.saveMember();
        var toMember = stepUtil.saveMember();

        // when
        registerFollowMemberUsecase.execute(fromMember.getId(), toMember.getId());

        // then
        var follows = followRepository.findAllByFromMemberId(fromMember.getId());
        assertEquals(1, follows.size());

        var result = follows.get(0);
        assertEquals(fromMember.getId(), result.getFromMemberId());
        assertEquals(toMember.getId(), result.getToMemberId());
    }
}
