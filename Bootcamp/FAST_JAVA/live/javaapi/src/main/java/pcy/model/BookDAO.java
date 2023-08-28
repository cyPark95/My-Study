package pcy.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// DAO(class): JDBC -> Mapper(interface): MyBatis -> Repository(interface): JPA
//                     Connection POLL -> HikariCP

public class BookDAO {  // CRUD

    private Connection connection;
    private Statement st;           // x
    private PreparedStatement ps;   // SQL 문자에 파라미터가 있는 경우에 사용
    private ResultSet rs;

    private void getConnection() {
        String url = "jdbc:mysql://localhost:3306/database";
        String username = "root";
        String password = "password";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dbClose() {
        try {
            if (rs != null) {
                rs.close();
            }

            if (ps != null) {
                ps.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 저장기능
    public int save(Book book) {
        // SQL 문장에 들어있는 ? => 파라미터
        // 미완성 SQL
        String sql = """
                INSERT INTO book(title, company, name, price) 
                VALUES(?, ?, ?, ?)
                """;
        getConnection();

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getCompany());
            ps.setString(3, book.getName());
            ps.setInt(4, book.getPrice());

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbClose();
        }

        return 0;
    }

    public List<Book> findAll() {
        String sql = """
                SELECT *
                FROM book
                """;
        getConnection();

        List<Book> books = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String title = resultSet.getString("title");
                String company = resultSet.getString("company");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");

                // 묶고(VO) -> 담고(List)
                books.add(new Book(id, title, company, name, price));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
    }
}
