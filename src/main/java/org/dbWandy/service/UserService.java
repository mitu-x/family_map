package org.dbWandy.service;

import org.dbWandy.pojo.User;

public interface UserService {
    boolean isUsernameExist(String username);

    String getPasswordByName(String username);

    boolean insertUser(User user);
}
