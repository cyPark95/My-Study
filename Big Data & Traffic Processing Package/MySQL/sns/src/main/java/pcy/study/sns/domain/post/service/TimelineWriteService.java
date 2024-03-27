package pcy.study.sns.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pcy.study.sns.domain.post.entity.Timeline;
import pcy.study.sns.domain.post.repository.TimelineJdbcRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TimelineWriteService {

    private final TimelineJdbcRepository timelineJdbcRepository;

    public void deliveryToTimeline(Long postId, List<Long> toMemberIds) {
        var timelines = toMemberIds.stream()
                .map(memberId -> toTimeline(postId, memberId))
                .toList();

        timelineJdbcRepository.bulkInsert(timelines);
    }

    private Timeline toTimeline(Long postId, Long memberId) {
        return Timeline.builder()
                .postId(postId)
                .memberId(memberId)
                .build();
    }
}
