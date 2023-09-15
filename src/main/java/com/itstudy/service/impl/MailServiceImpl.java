package com.itstudy.service.impl;

import com.itstudy.code.Code;
import com.itstudy.domain.ToEmail;
import com.itstudy.exception.BusinessException;
import com.itstudy.exception.SystemException;
import com.itstudy.service.IMaiService;
import com.sun.org.apache.bcel.internal.generic.FALOAD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import java.util.Arrays;
import java.util.Date;

/**
 * Description: MailServiceImpl
 * <br></br>
 * className: MailServiceImpl
 * <br></br>
 * packageName: com.itstudy.service.impl
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/9/6 20:35
 */
@Service
public class MailServiceImpl implements IMaiService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ITemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String sendEmail;

    /**
     * Description: sendEmail设置邮件信息并发送
     * @return java.lang.Boolean
     * @author jinhui-huang
     * @Date 2023/9/6
     * */
    @Override
    public Boolean sendEmail(ToEmail toEmail) {
        if (toEmail.getToUser() == null || toEmail.getToUser().isEmpty()) {
            throw new BusinessException(Code.BUSINESS_ERR, "邮箱收件人不能为空");
        }
        if (toEmail.getContent() == null || toEmail.getContent().isEmpty()) {
            throw new BusinessException(Code.BUSINESS_ERR, "邮箱验证码不能为空");
        }
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage());
            /*发件人*/
            mimeMessageHelper.setFrom(sendEmail);
            /*收件人*/
            mimeMessageHelper.setTo(toEmail.getToUser().split(","));
            /*设置邮箱验证码*/
            Context context = new Context();
            context.setVariable("verifyCode", Arrays.asList(toEmail.getContent().split("")));
            String process = templateEngine.process("EmailVerificationCode.html", context);
            /*邮件内容*/
            mimeMessageHelper.setText(process, true);
            /*邮件发送时间*/
            mimeMessageHelper.setSentDate(new Date());
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new SystemException(Code.SYSTEM_TIMEOUT_ERR, "发送邮件失败");
        }
    }
}
