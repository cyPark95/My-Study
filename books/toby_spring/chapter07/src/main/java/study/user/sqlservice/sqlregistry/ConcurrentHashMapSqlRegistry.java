package study.user.sqlservice.sqlregistry;

import study.user.sqlservice.exception.SqlNotFoundException;
import study.user.sqlservice.exception.SqlUpdateFailureException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapSqlRegistry implements UpdatableSqlRegistry {

    private final Map<String, String> sqlMap = new ConcurrentHashMap<>();

    @Override
    public void updateSql(String key, String sql) throws SqlUpdateFailureException {
        if(sqlMap.get(key) == null) {
            throw new SqlUpdateFailureException(String.format("%s에 해당하는 SQL을 찾을 수 없습니다.", key));
        }

        sqlMap.put(key, sql);
    }

    @Override
    public void updateSql(Map<String, String> sqlmap) throws SqlUpdateFailureException {
        for (Map.Entry<String, String> entry : sqlmap.entrySet()) {
            updateSql(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void registerSql(String key, String sql) {
        sqlMap.put(key, sql);
    }

    @Override
    public String findSql(String key) throws SqlNotFoundException {
        String sql = sqlMap.get(key);
        if (sql == null) {
            throw new SqlNotFoundException((String.format("%s를 이용해서 SQL을 찾을 수 없습니다.", key)));
        }

        return sql;
    }
}
