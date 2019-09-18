package com.shcepp.shdippsvr.business.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by apple on 2017/8/1.
 */

@Component
public class UserConfig implements BaseConfig {
    
    //默认组织代码
    public static String DEFAULT_ORG_CODE;
    
    //认证服务地址
    public static String EPOA_URL;
    
    //启用验证码
    public static String CAPTCHA_SWITCH;

    //client_id
    public static String CLIENT_ID = "F1927A432776AB6FD2CE7E6FB9114820";
    
    @Value("${shdippsvrcache.DEFAULT_ORG_CODE:SHDIPP_ORG}")
    public void setDefaultOrgCode(String defaultOrgCode){
        DEFAULT_ORG_CODE = defaultOrgCode;
    }
    
    @Value("${shdippsvrcache.EPOA_URL:http://192.168.128.111:8080/epoa}")
    public void setEpoaUrl(String epoaUrl){
        EPOA_URL = epoaUrl;
    }
    
//    @Value("${shdippsvrcache.CLIENT_ID:F1927A432776AB6FD2CE7E6FB9114820")
    public void setClientId(String clientId){
        CLIENT_ID = clientId;
    }
    
    @Value("${shdippsvrcache.CAPTCHA_SWITCH:1}")
    public void setCaptchaSwitch(String captchaSwitch){
        CAPTCHA_SWITCH = captchaSwitch;
    }
  
}
