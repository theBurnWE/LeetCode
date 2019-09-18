package com.shcepp.shdippsvr.sys.util.mail;

import kr.pe.kwonnam.slf4jlambda.LambdaLogger;
import kr.pe.kwonnam.slf4jlambda.LambdaLoggerFactory;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class MailUtils {
    
    private static final LambdaLogger logger = LambdaLoggerFactory.getLogger(MailUtils.class);
    private static Properties p;

    
    /**
     * 方法描述：发送邮件
     *
     * @param title
     * @param context
     * @param emails
     * @author liming
     * @time 2011-7-19 下午03:45:08
     */
    public static void sendEmail(String mailFrom, String host, String userName,
                                 String userPassword, String title, String context, String[] emails) {
        logger.info("sendEmail called");
        logger.info("mailFrom:" + mailFrom);
        logger.info("host:" + host);
        logger.info("context:" + context);
        
        try {
            if(emails != null && emails.length > 0) {
                if(p == null) {
                    try {
                        p = new Properties();
                        p.put("mail.smtp.auth", "false");
                        p.put("mail.from.address", "no-reply@shcepp.com");
                        p.put("mail.smtp.host", host);
                        p.put("mail.user.name", userName);
                        p.put("mail.user.pass", userPassword);
                        p.put("mail.transport.protocol", "smtp");
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }
                }
                if(p != null) {
                    // 建立会话
                    Session session = Session.getInstance(p);
                    Message msg = new MimeMessage(session); // 建立信息
                    msg.setFrom(new InternetAddress(mailFrom)); // 发件人
                    msg.setSentDate(new Date()); // 发送日期
                    msg.setContent(context, "text/html; charset=utf-8");
                    msg.setSubject(title); // 主题
                    for(String add : emails) {
                        msg.setRecipient(Message.RecipientType.TO,
                                new InternetAddress(add)); // 收件人
                        // 邮件服务器进行验证
                        Transport tran = session.getTransport("smtp");
                        tran.connect(host, userName, userPassword);
                        tran.sendMessage(msg, msg.getAllRecipients()); // 发送邮件
                        logger.info("to:" + add);
                    }
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
