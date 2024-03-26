package pcy.study.sns.domain.post.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pcy.study.sns.common.IntegrationTest;
import pcy.study.sns.domain.post.entity.Timeline;
import pcy.study.sns.domain.post.repository.TimelineRepository;
import pcy.study.sns.util.CursorRequest;
import pcy.study.sns.util.TimelineAssertUtil;
import pcy.study.sns.util.TimelineFixtureFactory;

import java.util.List;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IntegrationTest
class TimelineReadServiceTest {

    public static final Long MEMBER_ID = -1L;

    @Autowired
    private TimelineReadService timelineReadService;

    @Autowired
    private TimelineRepository timelineRepository;

    @Test
    @DisplayName("타임라인 조회")
    void getTimelines() {
        // given
        int size = 5;
        var timelines = LongStream.range(0, size + 1)
                .mapToObj(l -> {
                    var timeline = TimelineFixtureFactory.createTimeline(MEMBER_ID, -l);
                    return timelineRepository.save(timeline);
                })
                .toList();
        var key = getMaxId(timelines) + 1;

        var cursorRequest = new CursorRequest(key, size);

        // when
        var results = timelineReadService.getTimelines(MEMBER_ID, cursorRequest);

        // then
        assertTrue(results.nextCursorRequest().hasKey());
        assertEquals(size, results.body().size());

        for (int i = 0; i < size; i++) {
            TimelineAssertUtil.assertEqualsTimeline(timelines.get(size - i), results.body().get(i));
        }
    }

    private Long getMaxId(List<Timeline> timelines) {
        return timelines.stream()
                .mapToLong(Timeline::getId)
                .max()
                .orElseThrow();
    }
}
