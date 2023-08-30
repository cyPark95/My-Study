package pcy.st_0830;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// 1. JDBC
// Java 코드와 SQL 쿼리 분리(XML: Mapper SML file)
// Java 코드 <--- Mapping(매핑) ---> SQL 쿼리
//            2. MyBatis API(WEB)

// SQL 쿼리를 사용하지 않는 방법?
// Java 에서 SQL 쿼리를 자동으로 생성: save() -> Object(member) INSERT INTO table SQL ~
// 3. Hibernate(하이버네이트): ORM
// 4. Spring Data JPA(Spring Boot)
public class XmlQueryEx {

    public static void main(String[] args) {
        // 책 전체 목록 가져오기
        String url = "jdbc:mysql://localhost:3306/database";
        String username = "root";
        String password = "password";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 연결객체 생성
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                String sql = readSqlStatement("db.xml", "selectBooks");

                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String title = rs.getString("title");
                    int price = rs.getInt("price");
                    System.out.println("[" + title + "] 가격: " + price);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String readSqlStatement(String fileName, String queryId) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(fileName));

            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            String expression = "/queries/select[@id='" + queryId + "']";
            Node node = (Node) xpath.evaluate(expression, document, XPathConstants.NODE);

            if (node != null) {
                return node.getTextContent().trim();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
