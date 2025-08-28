package study.user.sqlservice.sqlregistry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import study.user.sqlservice.exception.SqlNotFoundException;
import study.user.sqlservice.exception.SqlUpdateFailureException;
import study.user.sqlservice.sqlregistry.updatable.UpdatableSqlRegistry;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class AbstractUpdatableSqlRegistryTest {

    protected UpdatableSqlRegistry sqlRegistry;

    @BeforeEach
    void setUp() {
        this.sqlRegistry = createUpdatableSqlRegistry();
        sqlRegistry.registerSql("KEY1", "SQL1");
        sqlRegistry.registerSql("KEY2", "SQL2");
        sqlRegistry.registerSql("KEY3", "SQL3");
    }

    @Test
    void find() {
        checkFind("SQL1", "SQL2", "SQL3");
    }

    @Test
    void unknownKey() {
        assertThrows(SqlNotFoundException.class, () -> sqlRegistry.findSql("UNKNOWN_KEY") );
    }

    @Test
    void updateSingle() {
        sqlRegistry.updateSql("KEY2", "Modified2");
        checkFind("SQL1", "Modified2", "SQL3");
    }

    @Test
    void updateMulti() {
        Map<String, String> sqlmap = new HashMap<>();
        sqlmap.put("KEY1", "Modified1");
        sqlmap.put("KEY3", "Modified3");

        sqlRegistry.updateSql(sqlmap);
        checkFind("Modified1", "SQL2", "Modified3");
    }

    @Test
    void updateWithNotExistingKey() {
        assertThrows(SqlUpdateFailureException.class, () -> sqlRegistry.updateSql("UNKNOWN_KEY", "SQL"));
    }

    abstract protected UpdatableSqlRegistry createUpdatableSqlRegistry();

    protected void checkFind(String expected1, String expected2, String expected3) {
        assertEquals(expected1, sqlRegistry.findSql("KEY1"));
        assertEquals(expected2, sqlRegistry.findSql("KEY2"));
        assertEquals(expected3, sqlRegistry.findSql("KEY3"));
    }
}
