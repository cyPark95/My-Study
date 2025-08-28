package study.user.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import study.user.domain.Level;
import study.user.domain.User;

import javax.sql.DataSource;
import java.util.List;

public class UserDaoJdbc implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate();
        this.jdbcTemplate.setDataSource(dataSource);
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
        String sql = """
                insert into 
                users(id, name, password, level, login, recommend, email)
                values(?, ?, ?, ?, ?, ?, ?)
                """;

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

    public List<User> getAll() {
        String sql = """
                select * from users order by id
                """;

        return jdbcTemplate.query(sql, userMapper);
    }

    public User get(String id) {
        String sql = """
                select * from users where id = ?
                """;

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, userMapper);
    }

    public void deleteAll() {
        String sql = """
                delete from users
                """;

        jdbcTemplate.update(connection -> connection.prepareStatement(sql));
    }

    public int getCount() {
        String sql = """
                select count(*) from users
                """;

        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public void update(User user) {
        String sql = """
                update users 
                set name = ?, password = ?, level = ?, login = ?, recommend = ?, email = ? where id = ?
                """;

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
