package org.javaboy.mail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

@SpringBootTest
class MailApplicationTests {

    @Autowired
    JavaMailSender javaMailSender;

    /**
     * 简单邮件发送
     */
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

    /**
     * 带附件邮件发送
     */
    @Test
    void sendEnclosureMail() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setSubject("这是测试带附件邮件主题");
        mimeMessageHelper.setText("这是测试带附件邮件内容");
        mimeMessageHelper.setFrom("1207350458@qq.com");
        mimeMessageHelper.setSentDate(new Date());
        mimeMessageHelper.setTo("myarctic@163.com");
        mimeMessageHelper.addAttachment("mail.txt", new File("E:\\ws\\vhr\\mail\\src\\main\\resources\\static\\mail.txt"));
        javaMailSender.send(mimeMessage);
    }

}
