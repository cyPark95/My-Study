package pcy.st_0825;

// JDBC Programing
import java.sql.*;  // interface(규약) ---> Driver

public class JdbcEx {

    public static void main(String[] args) {
        // 1. JDBC 드라이버 설치(API)
        // 2. 접속 URL, username, password
        String url = "jdbc:mysql://localhost:3306/database";
        String username = "root";
        String password = "password";

        String title = "자바의 정석";
        String company = "도우 출판사";
        String name = "남궁성";
        int price = 30000;

        // 3. 드라이버 로딩(MySQL 드라이버 클래스를 메모리에 로딩: 객체 생성
        // 정적로딩 -> 동적로딩
        // DriverManager driver = com.mysql.cj.jdbc.Driver()
        try {
            // 문자열(com.mysql.cj.jdbc.Driver) --> 객체 생성(new com.mysql.cj.jdbc.Driver())
            // 자바 기술: 리플렉션(Reflection), @(어노테이션) -> Spring
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 1. 연결객체 생성
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                System.out.println("연결성공");

                // 2. SQL 문장 만들기(변수 => "+ 변수 +")
                //                                                                    1  2  3  4
                // String sql = "INSERT INTO book(title, company, name, price) VALUES(?, ?, ?, ?)";
                String sql = "INSERT INTO book(title, company, name, price) VALUES('" + title + "', '" + company + "', '" + name + "', " + price + ")";
                // 3. SQL 문장 전송객체 생성
                Statement statement = connection.createStatement();
                int count = statement.executeUpdate(sql);   // 실행
                if (count > 0) {
                    System.out.println("저장 성공");
                } else {
                    System.out.println("저장 실패");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
