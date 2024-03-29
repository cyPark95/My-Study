package pcy.study.sns.domain.post.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import pcy.study.sns.domain.post.entity.Post;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostJdbcRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void bulkInsert(List<Post> posts) {
        var sql = """
                INSERT INTO post (member_id, contents, created_date, like_count, version, created_at)
                VALUES (:memberId, :contents, :createdDate, :likeCount, :version, :createdAt)
                """;

        SqlParameterSource[] params = posts.stream()
                .map(BeanPropertySqlParameterSource::new)
                .toArray(SqlParameterSource[]::new);

        namedParameterJdbcTemplate.batchUpdate(sql, params);
    }
}
