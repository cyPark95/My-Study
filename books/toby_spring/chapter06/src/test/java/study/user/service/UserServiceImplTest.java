package study.user.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import study.user.dao.MockUserDao;
import study.user.dao.UserDao;
import study.user.domain.Level;
import study.user.domain.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

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

    private void checkUserAndLevel(User user, String expectedId, Level expectedLevel) {
        assertEquals(expectedId, user.getId());
        assertEquals(expectedLevel, user.getLevel());
    }
}
