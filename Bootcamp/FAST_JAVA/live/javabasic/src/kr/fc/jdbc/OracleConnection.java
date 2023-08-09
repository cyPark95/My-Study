package kr.fc.jdbc;

// JDBC Driver -> 벤더사(Oracle)에서 제공 -> Oracle 전용 JDBC Driver 다운로드(API)
public class OracleConnection implements JdbcConnection {

    @Override
    public void getConnection(String url, String username, String password) {
        System.out.println("Oracle 접속 시도");
    }
}
