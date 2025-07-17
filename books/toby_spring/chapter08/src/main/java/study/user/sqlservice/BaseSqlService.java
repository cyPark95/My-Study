package study.user.sqlservice;

import jakarta.annotation.PostConstruct;
import study.user.sqlservice.exception.SqlNotFoundException;
import study.user.sqlservice.exception.SqlRetrievalFailureException;
import study.user.sqlservice.sqlreader.SqlReader;
import study.user.sqlservice.sqlregistry.SqlRegistry;

public class BaseSqlService implements SqlService {

    protected SqlReader sqlReader;

    protected SqlRegistry sqlRegistry;

    public void setSqlReader(SqlReader sqlReader) {
        this.sqlReader = sqlReader;
    }

    public void setSqlRegistry(SqlRegistry sqlRegistry) {
        this.sqlRegistry = sqlRegistry;
    }

    @PostConstruct
    public void loadSql() {
        sqlReader.read(sqlRegistry);
    }

    @Override
    public String getSql(String key) throws SqlRetrievalFailureException {
        try {
            return sqlRegistry.findSql(key);
        } catch (SqlNotFoundException e) {
            throw new SqlRetrievalFailureException(e);
        }
    }
}
