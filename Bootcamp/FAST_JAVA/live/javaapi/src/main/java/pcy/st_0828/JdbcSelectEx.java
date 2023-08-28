package pcy.st_0828;

import pcy.model.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcSelectEx {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/database";
        String username = "root";
        String password = "password";

        // 책 전체 목록을 출력하세요.
        String sql = """
                SELECT * 
                FROM book
                """;

        List<Book> books = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(url, username, password)) {
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

                books.forEach(System.out::println);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
