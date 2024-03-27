package pcy.study.sns.domain.post.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import pcy.study.sns.domain.post.entity.Timeline;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TimelineJdbcRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void bulkInsert(List<Timeline> timelines) {
        var sql = """
                INSERT INTO Timeline (memberId, postId, createdAt)
                VALUES (:memberId, :postId, :createdAt)
                """;

        SqlParameterSource[] params = timelines.stream()
                .map(BeanPropertySqlParameterSource::new)
                .toArray(SqlParameterSource[]::new);

        namedParameterJdbcTemplate.batchUpdate(sql, params);
    }
}
