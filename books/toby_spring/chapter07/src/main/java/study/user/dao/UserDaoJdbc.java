package study.user.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import study.user.domain.Level;
import study.user.domain.User;
import study.user.sqlservice.SqlService;

import javax.sql.DataSource;
import java.util.List;

public class UserDaoJdbc implements UserDao {

    private JdbcTemplate jdbcTemplate;

    private SqlService sqlService;

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
        jdbcTemplate.update(
                sqlService.getSql("userAdd"),
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
        return jdbcTemplate.queryForObject(sqlService.getSql("userGet"), new Object[]{id}, userMapper);
    }

    public List<User> getAll() {
        return jdbcTemplate.query(sqlService.getSql("userGetAll"), userMapper);
    }

    public void deleteAll() {
        jdbcTemplate.update(connection -> connection.prepareStatement(sqlService.getSql("userDeleteAll")));
    }

    public int getCount() {
        return jdbcTemplate.queryForObject(sqlService.getSql("userGetCount"), Integer.class);
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update(
                sqlService.getSql("userUpdate"),
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
