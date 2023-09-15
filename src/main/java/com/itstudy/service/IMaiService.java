package com.itstudy.service;

import com.itstudy.domain.ToEmail;

/**
 * Description: IMaiService
 * <br></br>
 * className: IMaiService
 * <br></br>
 * packageName: com.itstudy.service
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/9/6 20:32
 */
public interface IMaiService {

    /**
     * Description: checkMail 设置邮件信息并发送
     * @return void
     * @author jinhui-huang
     * @Date 2023/9/6
     * */
    Boolean sendEmail(ToEmail toEmail);
}
