package pcy.study.sns.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pcy.study.sns.domain.post.converter.TimelineConverter;
import pcy.study.sns.domain.post.dto.TimelineDto;
import pcy.study.sns.domain.post.entity.Timeline;
import pcy.study.sns.domain.post.repository.TimelineRepository;
import pcy.study.sns.util.CursorRequest;
import pcy.study.sns.util.PageCursor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimelineReadService {

    private final TimelineRepository timelineRepository;
    private final TimelineConverter timelineConverter;

    public PageCursor<TimelineDto> getTimelines(Long memberId, CursorRequest cursorRequest) {
        var timelines = findAllBy(memberId, cursorRequest);
        var nextKey = getNextKey(timelines);
        return new PageCursor<>(cursorRequest.next(nextKey), timelineConverter.toDto(timelines));
    }

    private List<Timeline> findAllBy(Long memberId, CursorRequest cursorRequest) {
        if (cursorRequest.hasKey()) {
            return timelineRepository.findAllByLessThanIdAndInMemberIdOrderByIdDesc(cursorRequest.key(), memberId, cursorRequest.size());
        }

        return timelineRepository.findAllByMemberIdOrderByIdDesc(memberId, cursorRequest.size());
    }

    private static long getNextKey(List<Timeline> timelines) {
        return timelines.stream()
                .mapToLong(Timeline::getId)
                .min()
                .orElse(CursorRequest.NONE_KEY);
    }
}
