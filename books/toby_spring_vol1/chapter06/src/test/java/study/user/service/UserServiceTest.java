package study.user.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import study.user.dao.MockUserDao;
import study.user.dao.UserDao;
import study.user.domain.Level;
import study.user.domain.User;

import java.lang.reflect.Proxy;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:test-application-context.xml")
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserService testUserService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PlatformTransactionManager transactionManager;

    private List<User> users;

    @BeforeEach
    void setUp() {
        this.users = List.of(
                new User("bumjin", "박범진", "p1", Level.BASIC, User.MIN_LOGCOUNT_FOR_SILVER - 1, 0, "bumjin@email.com"),
                new User("joytouch", "강명성", "p2", Level.BASIC, User.MIN_LOGCOUNT_FOR_SILVER, 0, "joytouch@email.com"),
                new User("erwins", "신승한", "p3", Level.SILVER, 60, User.MIN_RECCOMEND_FOR_GOLD - 1, "erwins@email.com"),
                new User("madnite1", "이상호", "p4", Level.SILVER, 60, User.MIN_RECCOMEND_FOR_GOLD, "madnite1@email.com"),
                new User("green", "오민규", "p5", Level.GOLD, 100, Integer.MAX_VALUE, "green@email.com")
        );
    }

    @Test
    void upgradeLevels() throws Exception {
        UserServiceImpl userServiceImpl = new UserServiceImpl();

        MockUserDao mockUserDao = new MockUserDao(this.users);
        userServiceImpl.setUserDao(mockUserDao);

        MockMailSender mockMailSender = new MockMailSender();
        userServiceImpl.setMailSender(mockMailSender);

        userServiceImpl.upgradeLevels();

        List<User> updated = mockUserDao.getUpdated();
        assertEquals(2, updated.size());
        checkUserAndLevel(updated.get(0), "joytouch", Level.SILVER);
        checkUserAndLevel(updated.get(1), "madnite1", Level.GOLD);

        List<String> requests = mockMailSender.getRequests();
        assertEquals(2, requests.size());
        assertEquals(users.get(1).getEmail(), requests.get(0));
        assertEquals(users.get(3).getEmail(), requests.get(1));
    }

    @Test
    void add() {
        userDao.deleteAll();

        User userWithLevel = users.get(4);
        User userWithoutLevel = users.get(0);
        userWithoutLevel.setLevel(null);

        userService.add(userWithLevel);
        userService.add(userWithoutLevel);

        User userWithLevelRead = userDao.get(userWithLevel.getId());
        User userWithoutLevelRead = userDao.get(userWithoutLevel.getId());

        assertEquals(userWithLevel.getLevel(), userWithLevelRead.getLevel());
        assertEquals(Level.BASIC, userWithoutLevelRead.getLevel());
    }

    @Test
    void upgradeAllOrNothing() throws Exception {
        userDao.deleteAll();
        for (User user : users) {
            userDao.add(user);
        }

        try {
            testUserService.upgradeLevels();
        } catch (TestUserServiceException ignored) {
        }

        checkLevelUpgraded(false, users.get(1));
    }

    @Test
    void mockUpgradeLevels() throws Exception {
        UserServiceImpl userServiceImpl = new UserServiceImpl();

        UserDao mockUserDao = mock(UserDao.class);
        when(mockUserDao.getAll()).thenReturn(users);
        userServiceImpl.setUserDao(mockUserDao);

        MailSender mockMailSender = mock(MailSender.class);
        userServiceImpl.setMailSender(mockMailSender);

        userServiceImpl.upgradeLevels();

        verify(mockUserDao, times(2)).update(any(User.class));
        verify(mockUserDao, times(2)).update(any(User.class));
        verify(mockUserDao).update(users.get(1));
        assertEquals(Level.SILVER, users.get(1).getLevel());
        verify(mockUserDao).update(users.get(3));
        assertEquals(Level.GOLD, users.get(3).getLevel());


        ArgumentCaptor<SimpleMailMessage> mailMessageArg = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mockMailSender, times(2)).send(mailMessageArg.capture());
        List<SimpleMailMessage> mailMessages = mailMessageArg.getAllValues();
        assertEquals(users.get(1).getEmail(), mailMessages.get(0).getTo()[0]);
        assertEquals(users.get(3).getEmail(), mailMessages.get(1).getTo()[0]);
    }

    @Test
    void advisorAutoProxyCreator() {
        assertTrue(Proxy.isProxyClass(testUserService.getClass()));
    }

    @Disabled("H2 DB는 ReadOnly 속성을 무시한다.")
    @Test
    void readOnlyTransactionAttribute() {
        assertThrows(TransientDataAccessResourceException.class, () -> testUserService.getAll());
    }

    private void checkLevelUpgraded(boolean expectedUpgraded, User user) {
        User userUpdate = userDao.get(user.getId());
        if (expectedUpgraded) {
            assertEquals(user.getLevel().nextLevel(), userUpdate.getLevel());
        } else {
            assertEquals(user.getLevel(), userUpdate.getLevel());
        }
    }

    @Test
    @Transactional
    void transactionSync() {
        userService.deleteAll();

        userService.add(users.get(0));
        userService.add(users.get(1));
    }

    private void checkUserAndLevel(User user, String expectedId, Level expectedLevel) {
        assertEquals(expectedId, user.getId());
        assertEquals(expectedLevel, user.getLevel());
    }

    static class TestUserService extends UserServiceImpl {

        private final String id = "madnite1";

        @Override
        public void upgradeLevel(User user) {
            if (user.getId().equals(this.id)) {
                throw new TestUserServiceException();
            }

            super.upgradeLevel(user);
        }

        @Override
        public List<User> getAll() {
            List<User> users = super.getAll();
            for (User user : users) {
                super.update(user);
            }
            return null;
        }
    }

    static class TestUserServiceException extends RuntimeException {
    }
}
