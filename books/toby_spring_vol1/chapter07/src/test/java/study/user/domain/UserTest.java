package study.user.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        this.user = new User();
    }

    @Test
    void upgradeLevel() {
        for (Level level : Level.values()) {
            if(level.nextLevel() == null) {
                continue;
            }

            user.setLevel(level);
            user.upgradeLevel();
            assertEquals(level.nextLevel(), user.getLevel());
        }
    }

    @Test
    void cannotUpgradeLevel() {
        for (Level level : Level.values()) {
            if(level.nextLevel() != null) {
                continue;
            }

            user.setLevel(level);
            assertThrows(IllegalStateException.class, () -> user.upgradeLevel());
        }
    }
}
