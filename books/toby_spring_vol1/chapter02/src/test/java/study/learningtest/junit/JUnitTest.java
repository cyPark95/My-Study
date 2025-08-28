package study.learningtest.junit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:junit.xml")
public class JUnitTest {

    @Autowired
    private ApplicationContext context;

    private static Set<JUnitTest> testObjects = new HashSet<>();
    private static ApplicationContext contextObject = null;

    @Test
    void test1() {
        assertFalse(testObjects.contains(this));
        testObjects.add(this);

        if (contextObject == null) {
            assertNull(contextObject);
        } else {
            assertSame(context, contextObject);
        }
        contextObject = this.context;
    }

    @Test
    void test2() {
        assertFalse(testObjects.contains(this));
        testObjects.add(this);

        assertEquals(true, contextObject == null || contextObject == context);
        contextObject = this.context;
    }

    @Test
    void test3() {
        assertFalse(testObjects.contains(this));
        testObjects.add(this);

        assertTrue(contextObject == null || contextObject == context);
        contextObject = this.context;
    }
}
