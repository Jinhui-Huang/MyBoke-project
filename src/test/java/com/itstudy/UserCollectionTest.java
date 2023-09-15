package com.itstudy;

import com.itstudy.service.IUserCollectionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

/**
 * Description: UserCollectionTest
 * <br></br>
 * className: UserCollectionTest
 * <br></br>
 * packageName: com.itstudy
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/9/4 21:11
 */
@SpringBootTest
public class UserCollectionTest {
    @Autowired
    private IUserCollectionService userCollectionService;

    @Test
    void testSelectClickDateTime() {
        LocalDateTime time = userCollectionService.selectUserClick(1002, "2634692718@qq.com");
        System.out.println(time);
        System.out.println(time.plusHours(1).isBefore(LocalDateTime.now()));
    }

    @Test
    void testSelectEnabled() {
        System.out.println(userCollectionService.
                selectEnabledSee(1001, "2634692718@qq.com"));
    }

    @Test
    void testSelectUserList() {
        System.out.println(userCollectionService.
                selectUserCollection("2634692718@qq.com"));
    }

    @Test
    void testSelectUserClick() {
        System.out.println(userCollectionService.
                selectUserClick(1002, "hjhhjh2333@outlook.com"));
    }

}
