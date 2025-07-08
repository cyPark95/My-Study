package study.user.sqlservice;

import study.user.sqlservice.sqlreader.JaxbXmlSqlReader;
import study.user.sqlservice.sqlregistry.HashMapSqlRegistry;

public class DefaultSqlService extends BaseSqlService {

    public DefaultSqlService() {
        setSqlReader(new JaxbXmlSqlReader());
        setSqlRegistry(new HashMapSqlRegistry());
    }
}
