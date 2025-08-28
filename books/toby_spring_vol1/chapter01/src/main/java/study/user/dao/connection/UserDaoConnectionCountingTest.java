package study.user.dao.connection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import study.user.dao.UserDao;
import study.user.dao.factory.DaoFactory;
import study.user.domain.User;

import java.sql.SQLException;

public class UserDaoConnectionCountingTest {

    public static void main(String[] args) throws SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao userDao = context.getBean("userDaoWithCounting", UserDao.class);

        User user = new User("whiteship", "백기선", "married");
        userDao.add(user);

        System.out.println(user.getId() + "등록 성공");

        User user2 = userDao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());
        System.out.println(user2.getId() + "조회 성공");

        CountingConnectionMaker countingConnectionMaker = context.getBean("countingConnectionMaker", CountingConnectionMaker.class);
        System.out.printf("Connection Counter: %d%n", countingConnectionMaker.getCounter());
    }
}
