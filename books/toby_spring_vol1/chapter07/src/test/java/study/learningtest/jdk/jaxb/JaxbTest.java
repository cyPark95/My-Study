package study.learningtest.jdk.jaxb;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;
import study.user.sqlservice.jaxb.SqlType;
import study.user.sqlservice.jaxb.Sqlmap;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JaxbTest {

    @Test
    void readSqlmap() throws JAXBException, IOException {
        String contextPath = Sqlmap.class.getPackage().getName();
        JAXBContext context = JAXBContext.newInstance(contextPath);

        Unmarshaller unmarshaller = context.createUnmarshaller();

        Sqlmap sqlmap = (Sqlmap) unmarshaller.unmarshal(getClass().getResourceAsStream("/sqlmap.xml"));

        List<SqlType> sqls = sqlmap.getSql();

        assertEquals(3, sqls.size());
        assertEquals("add", sqls.get(0).getKey());
        assertEquals("insert", sqls.get(0).getValue());
        assertEquals("get", sqls.get(1).getKey());
        assertEquals("select", sqls.get(1).getValue());
        assertEquals("delete", sqls.get(2).getKey());
        assertEquals("delete", sqls.get(2).getValue());
    }
}
