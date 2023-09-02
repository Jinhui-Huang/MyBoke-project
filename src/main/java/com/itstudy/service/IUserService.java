package com.itstudy.service;

import com.itstudy.domain.User;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description: IUserService
 * <br></br>
 * className: IUserService
 * <br></br>
 * packageName: com.itstudy.service
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/8/31 12:46
 */
@Transactional
public interface IUserService {
    User selectUserByEmail(String email);

    Boolean countUserByEmail(String email);

    Boolean insertUser(User user);

    Boolean updateUser(User user);
}
