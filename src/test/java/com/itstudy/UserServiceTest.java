package com.itstudy;

import com.itstudy.domain.User;
import com.itstudy.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Description: UserServiceTest
 * <br></br>
 * className: UserServiceTest
 * <br></br>
 * packageName: com.itstudy
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/8/31 12:52
 */
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private IUserService userService;

    @Test
    void testGetUserByEmail() {
        String email = "2634692718@qq.com";
        User user = userService.selectUserByEmail(email);
        System.out.println(user);
    }

    @Test
    void testCountUser(){
        String email = "2634692718@qq.com";
        System.out.println(userService.countUserByEmail(email));
    }

    @Test
    void testInsertUser(){
        User user = new User();
        user.setUserEmail("2217181966@qq.com");
        user.setUserName("huian");
        user.setUserPassword("hjh1123456");
        System.out.println(userService.insertUser(user));
    }
}
