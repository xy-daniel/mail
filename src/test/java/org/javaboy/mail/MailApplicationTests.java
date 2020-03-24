package org.javaboy.mail;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Date;

@SpringBootTest
class MailApplicationTests {

    @Autowired
    JavaMailSender javaMailSender;

    @Test
    void sendMail() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("这是测试邮件主题");
        mailMessage.setText("这是测试邮件内容");
        mailMessage.setFrom("1207350458@qq.com");
        mailMessage.setSentDate(new Date());
        mailMessage.setTo("myarctic@163.com");
        //抄送
//        mailMessage.setCc();
        //密抄
//        mailMessage.setBcc();
        javaMailSender.send(mailMessage);
    }

}
