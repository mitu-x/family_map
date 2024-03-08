package org.dbWandy.controller;

import lombok.extern.slf4j.Slf4j;
import org.dbWandy.mapper.UserMapper;
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

        return "ok";
    }

    @RequestMapping("/get")
    public String sasve() {

        return "String123444222";
    }
}
