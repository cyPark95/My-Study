package study.user.service;

import study.user.dao.UserDao;
import study.user.domain.Level;
import study.user.domain.User;

public class DefaultUserLevelUpgradePolicy implements UserLevelUpgradePolicy {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean canUpgradeLevel(User user) {
        Level currentLevel = user.getLevel();

        return switch (currentLevel) {
            case Level.BASIC -> user.getLogin() >= User.MIN_LOGCOUNT_FOR_SILVER;
            case Level.SILVER -> user.getRecommend() >= User.MIN_RECCOMEND_FOR_GOLD;
            case Level.GOLD -> false;
            default -> throw new IllegalArgumentException("Unknown Level: " + currentLevel);
        };
    }

    @Override
    public void upgradeLevel(User user) {
        user.upgradeLevel();
        userDao.update(user);
    }
}
