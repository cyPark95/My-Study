package pcy.study.sns.util;

import pcy.study.sns.domain.post.entity.Timeline;

public class TimelineFixtureFactory {

    public static Timeline createTimeline(Long memberId, Long postId) {
        return Timeline.builder()
                .memberId(memberId)
                .postId(postId)
                .build();
    }
}
