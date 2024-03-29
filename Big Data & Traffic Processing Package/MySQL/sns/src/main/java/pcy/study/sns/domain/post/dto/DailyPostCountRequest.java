package pcy.study.sns.domain.post.dto;

import java.time.LocalDate;

public record DailyPostCountRequest(
        LocalDate firstDate,
        LocalDate lastDate
) {
}
