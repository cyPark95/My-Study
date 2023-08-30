package pcy.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// DAO(class): JDBC -> Mapper(interface): MyBatis -> Repository(interface): JPA
//                     Connection POLL -> HikariCP

public class BookDAO implements BookRepository {  // CRUD

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
    @Override
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

    @Override
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
        } finally {
            dbClose();
        }

        return books;
    }

    @Override
    public Book findById(Long id) { // findByTitle -> SELECT * FROM book WHERE title = ?
        String sql = "SELECT * FROM book WHERE id = ?";
        getConnection();

        Book book = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                book =  new Book(
                        rs.getLong("num"),
                        rs.getString("title"),
                        rs.getString("company"),
                        rs.getString("name"),
                        rs.getInt("price")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbClose();
        }

        return book;
    }

    @Override
    public int update(Book book) {
        String sql = "UPDATE book SET company = ?, name = ?, price = ? where num = ?";
        int cnt = 0;

        getConnection();
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, book.getCompany());
            ps.setString(2, book.getName());
            ps.setInt(3, book.getPrice());
            ps.setLong(4, book.getId());
            cnt = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbClose();
        }

        return cnt;
    }

    @Override
    public List<Book> findByTitleLike(String title) {
        String sql = "SELECT * FROM book WHERE title LIKE ?";
        getConnection();

        List<Book> books = new ArrayList<>();
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + title + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                books.add(new Book(
                        rs.getLong("num"),
                        rs.getString("title"),
                        rs.getString("company"),
                        rs.getString("name"),
                        rs.getInt("price")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbClose();
        }

        return books;
    }

    @Override
    public int delete(long id) {
        String sql = "DELETE FROM book WHERE id = ?";
        int cnt = 0;
        getConnection();
        try {
            ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            cnt = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbClose();
        }

        return cnt;
    }
}
