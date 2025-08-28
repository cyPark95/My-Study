package study;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class InitialDataLoader {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void initializeDatabase() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        ClassPathResource script = new ClassPathResource("sql/ddl.sql");
        databasePopulator.addScript(script);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);
    }
}
