package org.lmmarise.vue.msg.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lmmarise.vue.msg.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/15 1:39 上午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppTest.class)
@EnableAutoConfiguration
public class AppTest {

    @Autowired
    private MailService mailService;

    private final String jpeg = URLDecoder.decode(
            this.getClass().getResource("/咒印.jpeg").getPath()
    );
    private final String docx = URLDecoder.decode(
            this.getClass().getClassLoader().getResource("文档.docx").getPath()
    );

    @Test
    public void run() {
    }

    @Test
    public void sendMailWithImg() {
        mailService.sendMailWithImg("202727330@qq.com", "tsb.j@qq.com",
                "测试邮件主题(图片)",
                "<div>hello,这是一封带图片资源的邮件：" +
                        "这是图片1：<div><img src='cid:p01'/></div>" +
                        "这是图片2：<div><img src='cid:p02'/></div>" +
                        "</div>",
                new String[]{jpeg},
                new String[]{"p01"});
    }

    @Test
    public void sendAttachFileMail() {
        mailService.sendAttachFileMail("202727330@qq.com",
                "tsb.j@qq.com",
                "测试邮件主题",
                "测试邮件内容",
                new File(docx));
    }

    @Test
    public void sendSimpleMail() {
        mailService.sendSimpleMail("202727330@qq.com",
                "tsb.j@qq.com",
                "1470249098@qq.com",
                "测试邮件主题",
                "测试邮件内容");
    }

    @Autowired
    TemplateEngine templateEngine;

    @Test
    public void sendHtmlMailThymeleaf() {
        Context ctx = new Context();
        ctx.setVariable("username", "cxk");
        ctx.setVariable("gender", "男");
        String mail = templateEngine.process("mailtemplate.html", ctx);
        mailService.sendHtmlMail("202727330@qq.com", "tsb.j@qq.com", "测试邮件主题", mail);
    }

    @Test
    public void sendHtmlMail2() {
//        try {
//            Configuration configuration =
//                    new Configuration(Configuration.VERSION_2_3_0);
//            ClassLoader loader = SendmailApplication.class.getClassLoader();
//            configuration
//            .setClassLoaderForTemplateLoading(loader,"ftl");
//            Template template = configuration.getTemplate("mailtemplate.ftl");
//            StringWriter mail = new StringWriter();
//            User user = new User();
//            user.setGender("男");
//            user.setUsername("cxk");
//            template.process(user, mail);
//            mailService.sendHtmlMail("202727330@qq.com",
//                    "tsb.j@qq.com",
//                    "测试邮件主题",
//                    mail.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

}
