package org.dbWandy.controller;

import lombok.extern.slf4j.Slf4j;
import org.dbWandy.mapper.UserMapper;
import org.dbWandy.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {

    //    @Autowired
    UserMapper userMapper;

    public ApiController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @RequestMapping("/save")
    public String save() {
        for (User user : userMapper.selectList(null)) {
            System.out.println(user.getName());
        }
        return "String123444222";
    }

    @RequestMapping("/get")
    public String sasve() {
        for (User user : userMapper.selectList(null)) {
            System.out.println(user.getName());
        }
        return "String123444222";
    }
}
