package com.shcepp.shdippsvr.business.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by apple on 2017/8/1.
 */

@Component
public class MailConfig implements BaseConfig {
    

    //系统发件人
    public static String MAIL_USER;
    //邮件服务器
    public static String MAIL_SERVER;
    


    
    @Value("${shdippsvrcache.MAIL_USER:no-reply@shcepp.com}")
    public void setMailUser(String mailUser){
        MAIL_USER = mailUser;
    }
    
    @Value("${shdippsvrcache.MAIL_SERVER:mail.shcepp.com}")
    public void setMailServer(String mailServer){
        MAIL_SERVER = mailServer;
    }
  
}
