package pcy.st_0828;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JdbcDeleteEx {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/database";
        String username = "root";
        String password = "password";

        // 데이터 처리
        int id = 1;

        String sql = """
                DELETE 
                FROM book 
                WHERE id = 1
                """;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                Statement statement = connection.createStatement();
                int count = statement.executeUpdate(sql);
                if (count > 0) {
                    System.out.println("삭제 성공");
                } else {
                    System.out.println("삭제 실패");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
