package pcy.study.sns.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pcy.study.sns.domain.post.entity.Timeline;
import pcy.study.sns.domain.post.repository.TimelineRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimelineWriteService {

    private final TimelineRepository timelineRepository;

    public void deliveryToTimeline(Long postId, List<Long> toMemberIds) {
        var timeline = toMemberIds.stream()
                .map(memberId -> toTimeline(postId, memberId))
                .toList();

        timelineRepository.bulkInsert(timeline);
    }

    private static Timeline toTimeline(Long postId, Long memberId) {
        return Timeline.builder()
                .postId(postId)
                .memberId(memberId)
                .build();
    }
}
