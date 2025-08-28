package study.user.sqlservice.sqlreader;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import study.user.dao.UserDao;
import study.user.sqlservice.sqlregistry.SqlRegistry;
import study.user.sqlservice.jaxb.SqlType;
import study.user.sqlservice.jaxb.Sqlmap;

import java.io.InputStream;

public class JaxbXmlSqlReader implements SqlReader {

    private static final String DEFAULT_SQLMAP_FILE = "/sql/sqlmap.xml";

    private String sqlmapFile = DEFAULT_SQLMAP_FILE;

    public void setSqlmapFile(String sqlmapFile) {
        this.sqlmapFile = sqlmapFile;
    }

    @Override
    public void read(SqlRegistry sqlRegistry) {
        String contextPath = Sqlmap.class.getPackage().getName();

        try {
            JAXBContext context = JAXBContext.newInstance(contextPath);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            InputStream inputStream = UserDao.class.getResourceAsStream(sqlmapFile);
            Sqlmap sqlmap = (Sqlmap) unmarshaller.unmarshal(inputStream);

            for (SqlType sql : sqlmap.getSql()) {
                sqlRegistry.registerSql(sql.getKey(), sql.getValue());
            }
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
