package com.itstudy.controller;

import com.itstudy.code.Code;
import com.itstudy.domain.User;
import com.itstudy.exception.BusinessException;
import com.itstudy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Description: UserController
 * <br></br>
 * className: UserController
 * <br></br>
 * packageName: com.itstudy.controller
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/8/31 13:26
 */
@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;

    /**
     * Description: selectUser 用户登陆
     * @return com.itstudy.controller.Result
     * @author jinhui-huang
     * @Date 2023/8/31
     * */
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        User selectUser = userService.selectUserByEmail(user.getUserEmail());
        if (selectUser != null) {
            if (user.getUserPassword().equals(selectUser.getUserPassword())) {
                selectUser.setUserPassword("");
            } else {
                selectUser = null;
            }
        }
        Integer code = selectUser != null ? Code.GET_OK : Code.GET_ERR;
        String msg = selectUser != null ? "登陆成功" : "用户邮箱或密码错误";
        return new Result(code, selectUser, msg);
    }

    /**
     * Description: 用户注册
     * @return
     * @author jinhui-huang
     * @Date 2023/8/31
     * */
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        boolean flag = false;
        if (user != null) {
            flag = userService.countUserByEmail(user.getUserEmail());
            if (flag) {
                /*注册业务*/
                flag = userService.insertUser(user);
            }
        }
        Integer code = flag ? Code.SAVE_OK : Code.SAVE_ERR;
        String msg = flag ? "注册成功" : "用户已存在!";
        return new Result(code, null, msg);
    }

    @GetMapping("/user_email/{userEmail}")
    public Result judgeUserEmail(@PathVariable String userEmail) {
        System.out.println(userEmail);
        boolean flag = !userService.countUserByEmail(userEmail);
        Integer code = flag ? Code.GET_OK : Code.GET_ERR;
        String msg = flag ? "请更新你的密码" : "用户不存在!";
        return new Result(code, userEmail, msg);
    }

    @PutMapping("/reset_password")
    public Result resetPassword(@RequestBody User user) {
        Boolean flag = userService.updateUser(user);
        System.out.println(flag);
        Integer code = flag ? Code.UPDATE_OK : Code.UPDATE_ERR;
        String msg = flag ? "密码更新成功" : "密码更新失败";
        return new Result(code, user, msg);
    }

}
