package org.dbWandy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.dbWandy.mapper.UserMapper;
import org.dbWandy.pojo.User;
import org.dbWandy.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 查询用户名是否已经存在
     *
     * @param username 传入 用户名
     * @return int
     */
    @Override
    public boolean isUsernameExist(String username) {
        //创建一个wrapper--创建查询条件
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        //返回查询结果
        Long aLong = userMapper.selectCount(wrapper);
        return aLong > 0;
    }

    /**
     * 根据用户名查询密码
     *
     * @param username 传入 用户名
     * @return String
     */
    @Override
    public String getPasswordByName(String username) {
        //创建一个wrapper--创建查询条件
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        //返回查询结果
        User user = userMapper.selectOne(wrapper);
        return user.getPassword();
    }

    /**
     * 插入一个用户
     *
     * @param user 传入User实体
     * @return int
     */
    @Override
    public boolean insertUser(User user) {
        String username = user.getUsername();
        boolean usernameExist = isUsernameExist(username);
        return !usernameExist && userMapper.insert(user) != 0;
    }

    /**
     * 验证密码是否输入正确
     *
     * @param username 用户名
     * @param password 密码
     * @return boolean
     */
    public boolean checkLogin(String username, String password) {
        String passwd = getPasswordByName(username);
        return password.equals(passwd);
    }
}
