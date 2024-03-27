package pcy.study.sns.domain.post.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pcy.study.sns.common.IntegrationTest;
import pcy.study.sns.domain.post.repository.TimelineRepository;

import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@IntegrationTest
class TimelineWriteServiceTest {

    @Autowired
    private TimelineWriteService timelineWriteService;

    @Autowired
    private TimelineRepository timelineRepository;

    @Test
    @DisplayName("회원 목록 타임라인 저장한다.")
    void deliveryToTimeline() {
        // given
        var size = 5;
        var postId = -1L;
        var toMemberIds = LongStream.range(0, size)
                .boxed()
                .toList();

        // when
        timelineWriteService.deliveryToTimeline(postId, toMemberIds);

        // then
        var results = timelineRepository.findAll();
        assertEquals(size, results.size());

        for (int i = 0; i < size; i++) {
            assertEquals(toMemberIds.get(i), results.get(i).getMemberId());
        }
    }
}
