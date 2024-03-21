package pcy.study.sns.domain.post.dto;

import java.time.LocalDate;

public record DailyPostCountRequest(
        Long memberId,
        LocalDate firstDate,
        LocalDate lastDate
) {
}
