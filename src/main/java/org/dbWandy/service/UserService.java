package org.dbWandy.service;

import org.dbWandy.pojo.User;

public interface UserService {
    /**
     * 查询用户名是否已经存在
     * @param username 传入 用户名
     * @return int
     */
    boolean isUsernameExist(String username);

    /**
     * 根据用户名查询密码
     * @param username 传入 用户名
     * @return String
     */
    String getPasswordByName(String username);


    /**
     * 插入一个用户
     * @param user 传入User实体
     * @return int
     */
    boolean insertUser(User user);
}
