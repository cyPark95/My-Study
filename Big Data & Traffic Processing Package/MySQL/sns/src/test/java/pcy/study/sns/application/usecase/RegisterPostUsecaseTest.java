package pcy.study.sns.application.usecase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pcy.study.sns.common.IntegrationTest;
import pcy.study.sns.common.TestContainerTest;
import pcy.study.sns.domain.post.dto.PostCommand;
import pcy.study.sns.domain.post.repository.PostRepository;
import pcy.study.sns.domain.post.repository.TimelineRepository;
import pcy.study.sns.util.StepUtil;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@IntegrationTest
@DisplayName("게시글을 등록하면, 팔로워들의 타임라인이 저장된다.")
class RegisterPostUsecaseTest extends TestContainerTest {

    @Autowired
    private RegisterPostUsecase registerPostUsecase;

    @Autowired
    private StepUtil stepUtil;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TimelineRepository timelineRepository;

    @Test
    void execute() {
        // given
        var size = 3;
        stepUtil.saveFollowerMember(size);

        var contents = "Post Contents";
        var command = new PostCommand(contents);

        // when
        var id = registerPostUsecase.execute(stepUtil.followerToMember.getId(), command);

        // then
        assertNotNull(id);

        var result = postRepository.findById(id).orElseThrow();
        assertEquals(id, result.getId());
        assertEquals(contents, result.getContents());

        await()
                .atMost(5, TimeUnit.SECONDS)
                .untilAsserted(() -> {
                    var timelines = timelineRepository.findAll();
                    assertEquals(size, timelines.size());
                    for (int i = 0; i < size; i++) {
                        assertEquals(stepUtil.followerFromMembers.get(i).getId(), timelines.get(i).getMemberId());
                    }
                });
    }
}
