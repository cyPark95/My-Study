package study.user.service;

import study.user.domain.User;

public interface UserLevelUpgradePolicy {

    boolean canUpgradeLevel(User user);

    void upgradeLevel(User user);
}
