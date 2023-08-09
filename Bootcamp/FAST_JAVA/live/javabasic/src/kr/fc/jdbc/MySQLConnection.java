package kr.fc.jdbc;

// JDBC Driver -> 벤더사(MySQL)에서 제공 -> MySQL 전용 JDBC Driver 다운로드(API)
public class MySQLConnection implements JdbcConnection {

    @Override
    public void getConnection(String url, String username, String password) {
        System.out.println("MySQL 접속 시도");
    }
}
