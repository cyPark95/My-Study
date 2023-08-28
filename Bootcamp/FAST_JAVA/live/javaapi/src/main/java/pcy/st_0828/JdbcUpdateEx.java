package pcy.st_0828;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JdbcUpdateEx {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/database";
        String username = "root";
        String password = "password";

        int id = 1;
        String title = "Java의 정석";

        // JDBC -> MyBatis -> ORM(Hibernate) -> Spring JPA
        String sql = "UPDATE book\n" +
                "SET title = '" + title + "'\n" +
                "WHERE id = " + id;
        //UPDATE book
        //SET title = 'Java의 정석'
        //WHERE id = 1;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                // 수정: id가 1인 책의 제목(자바의 정석)을 변경하세요.(SQL)
                Statement statement = connection.createStatement();
                int count = statement.executeUpdate(sql);
                if (count > 0) {
                    System.out.println("수정 성공");
                } else {
                    System.out.println("수정 실패");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
