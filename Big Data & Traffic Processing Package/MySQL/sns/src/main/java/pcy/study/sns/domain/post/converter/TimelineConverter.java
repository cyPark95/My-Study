package pcy.study.sns.domain.post.converter;

import org.springframework.stereotype.Component;
import pcy.study.sns.domain.post.dto.TimelineDto;
import pcy.study.sns.domain.post.entity.Timeline;

import java.util.List;

@Component
public class TimelineConverter {

    public List<TimelineDto> toDto(List<Timeline> timelines) {
        return timelines.stream()
                .map(this::toDto)
                .toList();
    }

    public TimelineDto toDto(Timeline timeline) {
        return new TimelineDto(
                timeline.getId(),
                timeline.getMemberId(),
                timeline.getPostId()
        );
    }
}
