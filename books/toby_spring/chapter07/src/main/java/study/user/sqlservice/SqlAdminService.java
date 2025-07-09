package study.user.sqlservice;

import study.user.sqlservice.exception.SqlRetrievalFailureException;
import study.user.sqlservice.sqlregistry.UpdatableSqlRegistry;

public class SqlAdminService implements SqlService {

    private UpdatableSqlRegistry updatableSqlRegistry;

    public void setUpdatableSqlRegistry(UpdatableSqlRegistry updatableSqlRegistry) {
        this.updatableSqlRegistry = updatableSqlRegistry;
    }

    @Override
    public String getSql(String key) throws SqlRetrievalFailureException {
        throw new UnsupportedOperationException();
    }

    public void updateSql(String key, String sql) {
        updatableSqlRegistry.updateSql(key, sql);
    }
}
