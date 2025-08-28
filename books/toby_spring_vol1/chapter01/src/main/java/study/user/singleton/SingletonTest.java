package study.user.singleton;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import study.user.dao.factory.DaoFactory;
import study.user.dao.UserDao;

public class SingletonTest {

	public static void main(String[] args) {
		DaoFactory factory = new DaoFactory();
		UserDao dao1 = factory.userDao();
		UserDao dao2 = factory.userDao();

		System.out.println("DaoFactory Objects :");
		System.out.println(dao1);
		System.out.println(dao2);

		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);

		UserDao dao3 = applicationContext.getBean("userDao", UserDao.class);
		UserDao dao4 = applicationContext.getBean("userDao", UserDao.class);

		System.out.println("ApplicationContext Objects :");
		System.out.println(dao3);
		System.out.println(dao4);
	}
} 
