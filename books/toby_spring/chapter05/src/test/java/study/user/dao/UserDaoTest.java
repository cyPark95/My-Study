package study.user.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import study.user.domain.Level;
import study.user.domain.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
class UserDaoTest {

    @Autowired
    private UserDao userDao;

    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        user1 = new User("gyumee", "박성철", "springnol", Level.BASIC, 1, 0);
        user2 = new User("leegw700", "이길원", "springno2", Level.SILVER, 55, 10);
    }

    @Test
    void addAndGet() {
        userDao.deleteAll();
        assertEquals(0, userDao.getCount());

        userDao.add(user1);
        userDao.add(user2);
        assertEquals(2, userDao.getCount());

        User getUser1 = userDao.get(user1.getId());
        checkSameUser(user1, getUser1);

        User getUser2 = userDao.get(user2.getId());
        checkSameUser(user2, getUser2);
    }

    @Test
    void update() {
        userDao.deleteAll();

        userDao.add(user1);
        userDao.add(user2);

        user1.setName("오민규");
        user1.setPassword("springno6");
        user1.setLevel(Level.GOLD);
        user1.setLogin(1000);
        user1.setRecommend(999);
        userDao.update(user1);

        User user1Update = userDao.get(user1.getId());
        checkSameUser(user1, user1Update);

        User user2Same = userDao.get(user2.getId());
        checkSameUser(user2, user2Same);
    }

    private void checkSameUser(User expectedUser, User user) {
        assertEquals(expectedUser.getId(), user.getId());
        assertEquals(expectedUser.getName(), user.getName());
        assertEquals(expectedUser.getPassword(), user.getPassword());
        assertEquals(expectedUser.getLevel(), user.getLevel());
        assertEquals(expectedUser.getLogin(), user.getLogin());
        assertEquals(expectedUser.getRecommend(), user.getRecommend());
    }
}
