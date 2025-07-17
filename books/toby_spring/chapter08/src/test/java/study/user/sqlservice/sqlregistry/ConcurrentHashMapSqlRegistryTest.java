package study.user.sqlservice.sqlregistry;

import study.user.sqlservice.sqlregistry.updatable.ConcurrentHashMapSqlRegistry;

class ConcurrentHashMapSqlRegistryTest extends AbstractUpdatableSqlRegistryTest {

    @Override
    protected UpdatableSqlRegistry createUpdatableSqlRegistry() {
        return new ConcurrentHashMapSqlRegistry();
    }
}
