package study.user.sqlservice.sqlregistry;

import study.user.sqlservice.exception.SqlNotFoundException;

public interface SqlRegistry {

    void registerSql(String key, String sql);

    String findSql(String key) throws SqlNotFoundException;
}
