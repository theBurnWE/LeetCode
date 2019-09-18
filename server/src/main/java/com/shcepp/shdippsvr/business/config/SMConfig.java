package com.shcepp.shdippsvr.business.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by apple on 2017/8/1.
 */

@Component
public class SMConfig implements BaseConfig {
    
    //是否实际发送
    public static String SM_ACTRUAL_SEND;
    //短信服务用户id
    public static String SM_USER_ID;
    //短信服务账户
    public static String SM_ACCOUNT;
    //短信服务账户密码
    public static String SM_PASSWORD;
    //短信服务地址
    public static String SM_URL;
    
    @Value("${shdippsvrcache.SM_ACTRUAL_SEND:0}")
    public void setSmActrualSend(String smActrualSend){
        SM_ACTRUAL_SEND = smActrualSend;
    }

    @Value("${shdippsvrcache.SM_USER_ID:289}")
    public void setSmUserId(String smUserId){
        SM_USER_ID = smUserId;
    }
    
    @Value("${shdippsvrcache.SM_ACCOUNT:600113}")
    public void setSmAccount(String smAccount){
        SM_ACCOUNT = smAccount;
    }
    
    @Value("${shdippsvrcache.SM_PASSWORD:shkjds.com}")
    public void setSmPassword(String smPassword){
        SM_PASSWORD = smPassword;
    }
    
    @Value("${shdippsvrcache.SM_URL:http://118.178.182.159:8088/sms.aspx}")
    public void setSmUrl(String smUrl){
        SM_URL = smUrl;
    }
  
}
