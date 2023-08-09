package kr.fc.java.st_0809;

import kr.fc.jdbc.JdbcConnection;
import kr.fc.jdbc.MySQLConnection;
import kr.fc.jdbc.OracleConnection;

public class JdbcEx {

    public static void main(String[] args) {
        // 다형성 -> 클래스, 1. 상속, 2. 재정의, 3. UpCasting, 4.동적 바인딩

        // Oracle DBMS 접속
        JdbcConnection oracleConnection = new OracleConnection();
        oracleConnection.getConnection("jdbc:oracle:thin:@localhost:1521:database", "system", "manager");

        // MySQL DBMS 접속
        JdbcConnection mysqlConnection = new MySQLConnection();
        mysqlConnection.getConnection("jdbc:mysql://localhost:3306/database", "root", "password");
    }
}
