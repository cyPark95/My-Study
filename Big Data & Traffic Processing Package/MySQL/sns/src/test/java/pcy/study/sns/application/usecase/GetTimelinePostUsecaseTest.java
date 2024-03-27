package pcy.study.sns.application.usecase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pcy.study.sns.common.IntegrationTest;
import pcy.study.sns.util.CursorRequest;
import pcy.study.sns.util.PostAssertUtil;
import pcy.study.sns.util.StepUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

@IntegrationTest
@DisplayName("회원의 타임라인 게시글 목록을 조회한다.")
class GetTimelinePostUsecaseTest {

    @Autowired
    private GetTimelinePostUsecase getTimelinePostUsecase;

    @Autowired
    private StepUtil stepUtil;

    @Test
    void execute() {
        // given
        int size = 3;
        stepUtil.saveFollowingMember(size);
        var posts = stepUtil.saveMembersPost(stepUtil.toMembers);
        stepUtil.saveMemberTimelines(posts, stepUtil.fromMember);

        var cursorRequest = new CursorRequest(null, size);

        // when
        var result = getTimelinePostUsecase.execute(stepUtil.fromMember.getId(), cursorRequest);

        // then
        assertEquals(size, result.body().size());
        assertEquals(posts.size() - size + 1, result.nextCursorRequest().key());

        for (int i = 0; i < size; i++) {
            PostAssertUtil.assertEqualsPost(posts.get(i), result.body().get(i));
        }
    }
}
