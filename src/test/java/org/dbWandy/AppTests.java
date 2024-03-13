package org.dbWandy;

import org.dbWandy.pojo.User;
import org.dbWandy.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = FamilyMapApplication.class)
@RunWith(SpringRunner.class)
public class AppTests {

    @Autowired
    UserService userService;

    @Test
    public void login() {
        User user = new User();
        user.setUsername("JackMa");
        user.setAge(19);
        user.setTrueName("张三");
        user.setPassword("123456");
        boolean i = userService.insertUser(user);
        System.out.println(i);
    }

    public boolean ak() {
        System.out.println("ak...");
        return false;
    }

    public boolean bk() {
        System.out.println("bk...");
        return true;
    }

    @Test
    public void demo() {
        boolean a = ak() && bk();
        System.out.println(a);
    }
}
