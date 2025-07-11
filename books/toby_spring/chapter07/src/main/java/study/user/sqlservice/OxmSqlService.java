package study.user.sqlservice;

import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.oxm.Unmarshaller;
import study.user.dao.UserDao;
import study.user.sqlservice.exception.SqlRetrievalFailureException;
import study.user.sqlservice.jaxb.SqlType;
import study.user.sqlservice.jaxb.Sqlmap;
import study.user.sqlservice.sqlreader.SqlReader;
import study.user.sqlservice.sqlregistry.HashMapSqlRegistry;
import study.user.sqlservice.sqlregistry.SqlRegistry;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;

public class OxmSqlService implements SqlService {

    private final BaseSqlService baseSqlService = new BaseSqlService();

    private final OxmSqlReader oxmSqlReader = new OxmSqlReader();

    protected SqlRegistry sqlRegistry = new HashMapSqlRegistry();

    public void setSqlRegistry(SqlRegistry sqlRegistry) {
        this.sqlRegistry = sqlRegistry;
    }

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        oxmSqlReader.setUnmarshaller(unmarshaller);
    }

    public void setSqlmap(Resource sqlmap) {
        oxmSqlReader.setSqlmap(sqlmap);
    }

    @PostConstruct
    public void loadSql() {
        baseSqlService.setSqlReader(oxmSqlReader);
        baseSqlService.setSqlRegistry(sqlRegistry);

        baseSqlService.loadSql();
    }

    @Override
    public String getSql(String key) throws SqlRetrievalFailureException {
        return baseSqlService.getSql(key);
    }

    private class OxmSqlReader implements SqlReader {

        private static final String DEFAULT_SQLMAP_FILE = "/sql/sqlmap.xml";

        private Unmarshaller unmarshaller;

        private Resource sqlmap = new ClassPathResource(DEFAULT_SQLMAP_FILE, UserDao.class);

        public void setUnmarshaller(Unmarshaller unmarshaller) {
            this.unmarshaller = unmarshaller;
        }

        public void setSqlmap(Resource sqlmap) {
            this.sqlmap = sqlmap;
        }

        @Override
        public void read(SqlRegistry sqlRegistry) {
            try {
                Source source = new StreamSource(sqlmap.getInputStream());
                Sqlmap sqlmap = (Sqlmap) unmarshaller.unmarshal(source);

                for (SqlType sql : sqlmap.getSql()) {
                    sqlRegistry.registerSql(sql.getKey(), sql.getValue());
                }
            } catch (IOException e) {
                throw new IllegalArgumentException(String.format("%s을 가져올 수 없습니다.", sqlmap.getFilename()));
            }
        }
    }
}
