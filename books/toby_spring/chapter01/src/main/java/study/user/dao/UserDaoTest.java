package study.user.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import study.user.domain.User;

import java.sql.SQLException;

public class UserDaoTest {

    public static void main(String[] args) throws SQLException {
        ApplicationContext applicationContext = new GenericXmlApplicationContext("/application-Context.xml");
        UserDao userDao = applicationContext.getBean("userDao", UserDao.class);

        User user = new User("whiteship", "백기선", "married");

        userDao.add(user);

        System.out.println(user.getId() + "등록 성공");

        User user2 = userDao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());
        System.out.println(user2.getId() + "조회 성공");
    }
}
