package study.user.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import study.user.domain.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(User user) throws SQLException {
        Connection connection = dataSource.getConnection();

        String sql = """
                insert into users(id, name, password) values(?, ?, ?)
                """;

        PreparedStatement prepareStatement = connection.prepareStatement(sql);
        prepareStatement.setString(1, user.getId());
        prepareStatement.setString(2, user.getName());
        prepareStatement.setString(3, user.getPassword());

        prepareStatement.executeUpdate();

        prepareStatement.close();
        connection.close();
    }

    public User get(String id) throws SQLException {
        Connection connection = dataSource.getConnection();

        String sql = """
                select * from users where id = ?
                """;

        PreparedStatement prepareStatement = connection.prepareStatement(sql);
        prepareStatement.setString(1, id);

        ResultSet resultSet = prepareStatement.executeQuery();

        User user = null;
        if (resultSet.next()) {
            user = new User(
                    resultSet.getString("id"),
                    resultSet.getString("name"),
                    resultSet.getString("password")
            );
        }

        resultSet.close();
        prepareStatement.close();
        connection.close();

        if (user == null) {
            throw new EmptyResultDataAccessException(1);
        }

        return user;
    }

    public void deleteAll() throws SQLException {
        Connection connection = dataSource.getConnection();

        String sql = """
                delete from users
                """;

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    public int getCount() throws SQLException {
        Connection connection = dataSource.getConnection();

        String sql = """
                select count(*) from users
                """;

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return count;
    }
}
