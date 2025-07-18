package study.user.dao.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import study.user.dao.UserDao;

import javax.sql.DataSource;

@Configuration
public class DaoFactory {

    @Bean
    public UserDao userDao() {
        UserDao userDao = new UserDao();
        userDao.setDataSource(dataSource());
        return userDao;
    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost:3306/study");
        dataSource.setUsername("root");
        dataSource.setPassword("1q2w3e4r!");

        return dataSource;
    }
}
