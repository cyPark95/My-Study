package study.user.sqlservice;

import study.user.sqlservice.exception.SqlRetrievalFailureException;

public interface SqlService {

    String getSql(String key) throws SqlRetrievalFailureException;
}
