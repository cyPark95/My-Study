package pcy.study.sns.domain.follow.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import pcy.study.sns.domain.follow.entity.Follow;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FollowRepository {

    private static final String TABLE = "Follow";
    private static final RowMapper<Follow> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> Follow.builder()
            .id(resultSet.getLong("id"))
            .fromMemberId(resultSet.getLong("fromMemberId"))
            .toMemberId(resultSet.getLong("toMemberId"))
            .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
            .build();

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Follow> findAllByFromMemberId(Long fromMemberId) {
        var sql = String.format("SELECT * FROM %s WHERE fromMemberId = :fromMemberId", TABLE);
        var params = new MapSqlParameterSource()
                .addValue("fromMemberId", fromMemberId);
        return namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER);
    }

    public Follow save(Follow follow) {
        if (follow.getId() == null) {
            return insert(follow);
        }

        return follow;
    }

    private Follow insert(Follow follow) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName(TABLE)
                .usingGeneratedKeyColumns("id");
        SqlParameterSource params = new BeanPropertySqlParameterSource(follow);

        var id = simpleJdbcInsert.executeAndReturnKey(params).longValue();
        return Follow.builder()
                .id(id)
                .fromMemberId(follow.getFromMemberId())
                .toMemberId(follow.getToMemberId())
                .createdAt(follow.getCreatedAt())
                .build();
    }
}