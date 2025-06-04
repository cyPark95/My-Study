package study.user.dao;

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

        resultSet.next();

        User user = new User(
                resultSet.getString("id"),
                resultSet.getString("name"),
                resultSet.getString("password")
        );

        resultSet.close();
        prepareStatement.close();
        connection.close();

        return user;
    }
}
