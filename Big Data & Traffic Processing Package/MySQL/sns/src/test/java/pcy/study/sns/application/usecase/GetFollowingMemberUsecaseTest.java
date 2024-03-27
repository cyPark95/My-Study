package pcy.study.sns.application.usecase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pcy.study.sns.common.IntegrationTest;
import pcy.study.sns.util.MemberAssertUtil;
import pcy.study.sns.util.StepUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

@IntegrationTest
@DisplayName("회원이 팔로우한 회원 목록을 조회한다.")
class GetFollowingMemberUsecaseTest {

    @Autowired
    private GetFollowingMemberUsecase getFollowingMemberUsecase;

    @Autowired
    private StepUtil stepUtil;

    @Test
    void execute() {
        // given
        var size = 3;
        var fromMember = stepUtil.saveMember();
        var toMembers = stepUtil.saveMembers(size);
        stepUtil.following(fromMember, toMembers);

        // when
        var result = getFollowingMemberUsecase.execute(fromMember.getId());

        // then
        assertEquals(size, result.size());

        for (int i = 0; i < size; i++) {
            MemberAssertUtil.assertEqualsMember(toMembers.get(i), result.get(i));
        }
    }
}
