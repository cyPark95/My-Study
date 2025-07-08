package study.user.sqlservice.sqlregistry;

import study.user.sqlservice.exception.SqlNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class HashMapSqlRegistry implements SqlRegistry {

    private final Map<String, String> sqlMap = new HashMap<>();

    @Override
    public void registerSql(String key, String sql) {
        sqlMap.put(key, sql);
    }

    @Override
    public String findSql(String key) throws SqlNotFoundException {
        String sql = sqlMap.get(key);
        if(sql == null) {
            throw new SqlNotFoundException(String.format("%s를 이용해서 SQL을 찾을 수 없습니다.", key));
        }

        return sql;
    }
}
