package pcy.study.sns.domain.post.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pcy.study.sns.common.IntegrationTest;
import pcy.study.sns.domain.post.entity.Timeline;
import pcy.study.sns.domain.post.repository.TimelineRepository;
import pcy.study.sns.util.CursorRequest;
import pcy.study.sns.util.PostAssertUtil;
import pcy.study.sns.util.PostFixtureFactory;

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
        var timelines = saveTimelines(size + 1);
        var key = getMaxId(timelines);

        var cursorRequest = new CursorRequest(key, size);

        // when
        var results = timelineReadService.getTimelines(MEMBER_ID, cursorRequest);

        // then
        assertTrue(results.nextCursorRequest().hasKey());
        assertEquals(size, results.body().size());

        for (int i = 0; i < size; i++) {
            PostAssertUtil.assertEqualsTimeline(timelines.get(size - i - 1), results.body().get(i));
        }
    }

    @Test
    @DisplayName("Cursor Key 없이 타임라인 조회 - 최근순")
    void getTimelines_HasNotKey() {
        // given
        int size = 5;
        var timelines = saveTimelines(size + 1);
        var key = getMaxId(timelines) + 1;

        var cursorRequest = new CursorRequest(null, size);

        // when
        var results = timelineReadService.getTimelines(MEMBER_ID, cursorRequest);

        // then
        assertTrue(results.nextCursorRequest().hasKey());
        assertEquals(size, results.body().size());

        for (int i = 0; i < size; i++) {
            PostAssertUtil.assertEqualsTimeline(timelines.get(size - i), results.body().get(i));
        }
    }

    private List<Timeline> saveTimelines(int size) {
        return LongStream.range(0, size)
                .mapToObj(l -> {
                    var timeline = PostFixtureFactory.createTimeline(MEMBER_ID, l);
                    return timelineRepository.save(timeline);
                })
                .toList();
    }

    private Long getMaxId(List<Timeline> timelines) {
        return timelines.stream()
                .mapToLong(Timeline::getId)
                .max()
                .orElseThrow();
    }
}
