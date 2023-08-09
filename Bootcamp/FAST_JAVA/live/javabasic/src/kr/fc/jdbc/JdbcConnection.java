package kr.fc.jdbc;

public interface JdbcConnection {

    // DB 커넥션 연결
    // DB 종류마다 연결 방식 상이 -> 메서드 이름은 통일 시킬 수 있다.
    void getConnection(String url, String username, String password);
}
