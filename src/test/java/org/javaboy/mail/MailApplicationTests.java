package org.javaboy.mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * 发送带图片资源的邮件
     */
    @Test
    void picture() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setSubject("这是测试带图片邮件主题");
        mimeMessageHelper.setText("这是测试带图片邮件内容,这是第一张图图片<img src='cid:p01'/>,这是第二张图片<img src='cid:p02'/>", true);
        mimeMessageHelper.setFrom("1207350458@qq.com");
        mimeMessageHelper.setSentDate(new Date());
        mimeMessageHelper.setTo("myarctic@163.com");
        mimeMessageHelper.addInline("p01", new FileSystemResource(new File("E:\\ws\\20200324144437.jpg")));
        mimeMessageHelper.addInline("p02", new FileSystemResource(new File("E:\\ws\\20200324144437.jpg")));
        javaMailSender.send(mimeMessage);
    }

    //将thymeleaf渲染成一个html页面
    @Autowired
    TemplateEngine templateEngine;

    @Test
    void thymeleaf() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setSubject("这是测试thymeleaf邮件主题");
        Context context = new Context();
        context.setVariable("username", "丁代光");
        context.setVariable("position", "Java工程师");
        context.setVariable("dep", "产品研发部");
        context.setVariable("salary", "24K元/月");
        context.setVariable("joblevel", "高级工程师");
        String process = templateEngine.process("mail.html", context);
        mimeMessageHelper.setText(process, true);
        mimeMessageHelper.setFrom("1207350458@qq.com");
        mimeMessageHelper.setSentDate(new Date());
        mimeMessageHelper.setTo("myarctic@163.com");
        javaMailSender.send(mimeMessage);
    }

    @Test
    void freemarker() throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setSubject("这是测试freemarker邮件主题");
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_29);
        configuration.setClassLoaderForTemplateLoading(this.getClass().getClassLoader(),"templates");
        Template template = configuration.getTemplate("mail.ftlh");
        Map<String, Object> map = new HashMap<>();
        map.put("username", "丁代光");
        map.put("position", "Java工程师");
        map.put("dep", "产品研发部");
        map.put("salary", "24K元/月");
        map.put("joblevel", "高级工程师");
        StringWriter out = new StringWriter();
        template.process(map, out);
        mimeMessageHelper.setText(out.toString(), true);
        mimeMessageHelper.setFrom("1207350458@qq.com");
        mimeMessageHelper.setSentDate(new Date());
        mimeMessageHelper.setTo("myarctic@163.com");
        javaMailSender.send(mimeMessage);
    }
}
