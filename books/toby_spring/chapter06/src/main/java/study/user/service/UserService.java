package study.user.service;

import study.user.domain.User;

public interface UserService {

    void add(User user);

    void upgradeLevels();
}
