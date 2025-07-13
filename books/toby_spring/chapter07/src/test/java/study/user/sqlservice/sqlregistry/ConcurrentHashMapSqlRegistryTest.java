package study.user.sqlservice.sqlregistry;

import study.user.sqlservice.sqlregistry.updatable.ConcurrentHashMapSqlRegistry;
import study.user.sqlservice.sqlregistry.updatable.UpdatableSqlRegistry;

class ConcurrentHashMapSqlRegistryTest extends AbstractUpdatableSqlRegistryTest {

    @Override
    protected UpdatableSqlRegistry createUpdatableSqlRegistry() {
        return new ConcurrentHashMapSqlRegistry();
    }
}
