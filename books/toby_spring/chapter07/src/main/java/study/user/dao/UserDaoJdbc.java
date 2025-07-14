package study.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import study.user.domain.Level;
import study.user.domain.User;
import study.user.sqlservice.SqlService;

import javax.sql.DataSource;
import java.util.List;

@Component
public class UserDaoJdbc implements UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SqlService sqlService;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate();
        this.jdbcTemplate.setDataSource(dataSource);
    }

    public void setSqlService(SqlService sqlService) {
        this.sqlService = sqlService;
    }

    private final RowMapper<User> userMapper = (rs, rowNum) -> new User(
            rs.getString("id"),
            rs.getString("name"),
            rs.getString("password"),
            Level.valueOf(rs.getInt("level")),
            rs.getInt("login"),
            rs.getInt("recommend"),
            rs.getString("email")
    );

    public void add(final User user) {
        String sql = sqlService.getSql("userAdd");
        jdbcTemplate.update(
                sql,
                user.getId(),
                user.getName(),
                user.getPassword(),
                user.getLevel().intValue(),
                user.getLogin(),
                user.getRecommend(),
                user.getEmail()
        );
    }

    public User get(String id) {
        String sql = sqlService.getSql("userGet");
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, userMapper);
    }

    public List<User> getAll() {
        String sql = sqlService.getSql("userGetAll");
        return jdbcTemplate.query(sql, userMapper);
    }

    public void deleteAll() {
        String sql = sqlService.getSql("userDeleteAll");
        jdbcTemplate.update(sql);
    }

    public int getCount() {
        String sql = sqlService.getSql("userGetCount");
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public void update(User user) {
        String sql = sqlService.getSql("userUpdate");
        jdbcTemplate.update(
                sql,
                user.getName(),
                user.getPassword(),
                user.getLevel().intValue(),
                user.getLogin(),
                user.getRecommend(),
                user.getEmail(),
                user.getId()
        );
    }
}
