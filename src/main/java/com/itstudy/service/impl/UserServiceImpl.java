package com.itstudy.service.impl;

import com.itstudy.code.Code;
import com.itstudy.dao.UserDao;
import com.itstudy.domain.User;
import com.itstudy.exception.BusinessException;
import com.itstudy.exception.SystemException;
import com.itstudy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Description: UserServiceImpl
 * <br></br>
 * className: UserServiceImpl
 * <br></br>
 * packageName: com.itstudy.service.impl
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/8/31 12:46
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserDao userDao;


    @Override
    public User selectUserByEmail(String email) {
        try {
           return userDao.getUserByEmail(email);
        } catch (Exception e) {
            throw new SystemException(Code.SYSTEM_TIMEOUT_ERR, "服务器超时, 请重试!", e);
        }
    }

    @Override
    public Boolean countUserByEmail(String email) {
        try {
            return userDao.countUserByEmail(email) != 1;
        } catch (Exception e) {
            throw new SystemException(Code.SYSTEM_TIMEOUT_ERR, "服务器超时, 请重试!", e);
        }
    }

    @Override
    public Boolean insertUser(User user) {
        try {
            /*默认头像*/
            String[] img = {
                    "http://192.168.1.106:10086/static/img/user_img/img_0000.png",
                    "http://192.168.1.106:10086/static/img/user_img/img_1000.png",
                    "http://192.168.1.106:10086/static/img/user_img/img_1001.png",
            };
            user.setUserImg(img[new Random().nextInt(3)]);
            return userDao.insertUser(user) == 1;
        } catch (Exception e) {
            throw new SystemException(Code.SYSTEM_TIMEOUT_ERR, "服务器超时, 请重试!", e);
        }
    }

    @Override
    public Boolean updateUser(User user) {
        try {
            return userDao.updateUserByEmail(user) == 1;
        } catch (Exception e) {
            throw new SystemException(Code.SYSTEM_TIMEOUT_ERR, "服务器超时, 请重试!", e);
        }
    }
}
