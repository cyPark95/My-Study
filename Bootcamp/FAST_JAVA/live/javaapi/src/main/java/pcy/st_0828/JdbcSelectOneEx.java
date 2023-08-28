package pcy.st_0828;

import pcy.model.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcSelectOneEx {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/database";
        String username = "root";
        String password = "password";

        // 책 id가 1인 책 한권을 출력하세요.
        String sql = """
                SELECT * 
                FROM book 
                WHERE id = 1
                """;

        Book book = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                Statement statement = connection.createStatement();
                // statement.executeUpdate(sql) : INSERT, UPDATE, DELETE
                ResultSet resultSet = statement.executeQuery(sql);
                if (resultSet.next()) {
                    long id = resultSet.getLong("id");
                    String title = resultSet.getString("title");
                    String company = resultSet.getString("company");
                    String name = resultSet.getString("name");
                    int price = resultSet.getInt("price");

                    // 묶고(VO) -> 담고(List)
                    book = new Book(id, title, company, name, price);
                }
                if (book != null) {
                    System.out.println(book);
                } else {
                    System.out.println("데이터가 없습니다.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
