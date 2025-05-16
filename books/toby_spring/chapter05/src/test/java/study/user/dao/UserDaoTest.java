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
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
class UserDaoTest {

    @Autowired
    private UserDao userDao;

    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    void setUp() {
        user1 = new User("id1", "name1", "password1", Level.BASIC, 1, 0);
        user2 = new User("id2", "name2", "password2", Level.SILVER, 55, 10);
        user3 = new User("id3", "name3", "password3", Level.GOLD, 100, 40);
    }

    @Test
    void addAndGet() {
        assertNotNull(userDao);

        userDao.deleteAll();
        assertEquals(0, userDao.getCount());

        userDao.add(user1);
        userDao.add(user2);
        assertEquals(2, userDao.getCount());

        User getUser1 = userDao.get(user1.getId());
        assertEquals(user1.getName(), getUser1.getName());
        assertEquals(user1.getPassword(), getUser1.getPassword());

        User getUser2 = userDao.get(user2.getId());
        assertEquals(user2.getName(), getUser2.getName());
        assertEquals(user2.getPassword(), getUser2.getPassword());
    }
}
