package study.learningtest.spring.oxm;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import study.user.sqlservice.jaxb.SqlType;
import study.user.sqlservice.jaxb.Sqlmap;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:OxmTest-context.xml")
public class OxmTest {

    @Autowired
    private Unmarshaller unmarshaller;

    @Test
    void unmarshallSqlMap() throws XmlMappingException, IOException {
        Source xmlSource = new StreamSource(getClass().getResourceAsStream("/sqlmap.xml"));

        Sqlmap sqlmap = (Sqlmap) unmarshaller.unmarshal(xmlSource);

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
