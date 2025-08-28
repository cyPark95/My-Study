package study.user.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import study.user.domain.User;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:test-applicationContext.xml")
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    void setUp() {
        this.user1 = new User("gyumee", "박성철", "springnol");
        this.user2 = new User("leegm700", "이길원", "springno2");
        this.user3 = new User("bumjin", "박범진", "springno3");
    }

    @Test
    void addAndGet() throws SQLException {
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

    @Test
    void count() throws SQLException {
        userDao.deleteAll();
        assertEquals(0, userDao.getCount());

        userDao.add(user1);
        assertEquals(1, userDao.getCount());

        userDao.add(user2);
        assertEquals(2, userDao.getCount());

        userDao.add(user3);
        assertEquals(3, userDao.getCount());
    }

    @Test
    void getUserFailure() throws SQLException {
        userDao.deleteAll();
        assertEquals(0, userDao.getCount());

        assertThrows(EmptyResultDataAccessException.class, () -> userDao.get("unknown_id"));
    }
}
