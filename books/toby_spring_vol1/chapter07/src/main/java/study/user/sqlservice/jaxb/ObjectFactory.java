package study.user.sqlservice.jaxb;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {
    }

    public Sqlmap createSqlmap() {
        return new Sqlmap();
    }

    public SqlType createSqlType() {
        return new SqlType();
    }
}