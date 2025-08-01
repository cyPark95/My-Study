package study.user.sqlservice;

import study.user.sqlservice.exception.SqlRetrievalFailureException;

import java.util.Map;

public class SimpleSqlService implements SqlService {

    private Map<String, String> sqlMap;

    public void setSqlMap(Map<String, String> sqlMap) {
        this.sqlMap = sqlMap;
    }

    @Override
    public String getSql(String key) throws SqlRetrievalFailureException {
        String sql = sqlMap.get(key);
        if (sql == null) {
            throw new SqlRetrievalFailureException(String.format("%s에 대한 SQL을 찾을 수 없습니다.", key));
        }

        return sql;
    }
}
