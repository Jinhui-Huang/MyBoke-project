package com.itstudy.controller;

import com.itstudy.code.Code;
import com.itstudy.code.VerCodeGenerateUtil;
import com.itstudy.domain.ToEmail;
import com.itstudy.service.IMaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Description: ToEmailController
 * <br></br>
 * className: ToEmailController
 * <br></br>
 * packageName: com.itstudy.controller
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/9/6 20:49
 */

@CrossOrigin
@RestController
@RequestMapping("/email")
public class ToEmailController {
    @Autowired
    private IMaiService maiService;

    @GetMapping("{email}")
    public Result sendEmail(@PathVariable String email) {
        ToEmail toEmail = new ToEmail();
        toEmail.setToUser(email);
        if (toEmail.getToUser() == null) {
            return new Result(Code.GET_ERR, false, "参数错误, 请检查邮件内容");
        }
        String verCode = VerCodeGenerateUtil.generateVerCode();
        toEmail.setContent(verCode);
        Boolean flag = maiService.sendEmail(toEmail);
        Integer code = flag ? Code.GET_OK : Code.GET_ERR;
        String msg = flag ? "邮箱发送成功" : "邮箱发送失败";
        return new Result(code, true, msg);
    }

}
