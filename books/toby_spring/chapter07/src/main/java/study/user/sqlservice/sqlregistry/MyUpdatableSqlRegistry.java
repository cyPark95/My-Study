package study.user.sqlservice.sqlregistry;

import study.user.sqlservice.exception.SqlNotFoundException;
import study.user.sqlservice.exception.SqlUpdateFailureException;

import java.util.Map;

public class MyUpdatableSqlRegistry implements UpdatableSqlRegistry {

    @Override
    public void updateSql(String key, String sql) throws SqlUpdateFailureException {

    }

    @Override
    public void updateSql(Map<String, String> sqlmap) throws SqlUpdateFailureException {

    }

    @Override
    public void registerSql(String key, String sql) {

    }

    @Override
    public String findSql(String key) throws SqlNotFoundException {
        return "";
    }
}
