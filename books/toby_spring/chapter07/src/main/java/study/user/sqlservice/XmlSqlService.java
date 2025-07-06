package study.user.sqlservice;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import study.user.dao.UserDao;
import study.user.sqlservice.jaxb.SqlType;
import study.user.sqlservice.jaxb.Sqlmap;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class XmlSqlService implements SqlService {

    private final Map<String, String> sqlMap = new HashMap<>();

    public XmlSqlService() {
        String contextPath = Sqlmap.class.getPackage().getName();

        try {
            JAXBContext context = JAXBContext.newInstance(contextPath);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            InputStream inputStream = UserDao.class.getResourceAsStream("/sql/sqlmap.xml");
            Sqlmap sqlmap = (Sqlmap) unmarshaller.unmarshal(inputStream);

            for (SqlType sql : sqlmap.getSql()) {
                sqlMap.put(sql.getKey(), sql.getValue());
            }
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getSql(String key) throws SqlRetrievalFailureException {
        String sql = sqlMap.get(key);
        if(sql == null) {
            throw new SqlRetrievalFailureException(String.format("%s를 이용해서 SQL을 찾을 수 없습니다.", key));
        }

        return sql;
    }
}
