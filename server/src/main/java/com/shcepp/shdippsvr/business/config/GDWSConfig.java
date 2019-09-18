package com.shcepp.shdippsvr.business.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by apple on 2017/8/1.
 */
@Component
public class GDWSConfig implements BaseConfig {

    private static final long serialVersionUID = 8756306125036699308L;
    //GDWS地址配置
    @Value("${UPF_EASISPAY_GDWS_URL:http://192.168.128.87:8001/GDWS/GCCData}")
    private String upfEasipayGdwsUrl;


    @Value("${shdippsvrcache.UPF_EASISPAY_GDWS_THREAD_SIZE:3}")
    private int upfEasipayGdwsThreadSize;


    @Value("${shdippsvrcache.UPF_EASISPAY_GDWS_USER_NAME:pingpong}")
    private String upfEasipayGdwsUserName;


    @Value("${shdippsvrcache.UPF_EASISPAY_GDWS_USER_PASSWORD:pass1234}")
    private String upfEasipayGdwsUserPassword;


    @Value("${shdippsvrcache.UPF_EASISPAY_GDWS_USER_ENCRYPTION:}")
    private String upfEasipayGdwsUserEncryption;

    public String getUpfEasipayGdwsUrl() {
        return upfEasipayGdwsUrl;
    }

    public void setUpfEasipayGdwsUrl(String upfEasipayGdwsUrl) {
        this.upfEasipayGdwsUrl = upfEasipayGdwsUrl;
    }

    public int getUpfEasipayGdwsThreadSize() {
        return upfEasipayGdwsThreadSize;
    }

    public void setUpfEasipayGdwsThreadSize(int upfEasipayGdwsThreadSize) {
        this.upfEasipayGdwsThreadSize = upfEasipayGdwsThreadSize;
    }

    public String getUpfEasipayGdwsUserName() {
        return upfEasipayGdwsUserName;
    }

    public void setUpfEasipayGdwsUserName(String upfEasipayGdwsUserName) {
        this.upfEasipayGdwsUserName = upfEasipayGdwsUserName;
    }

    public String getUpfEasipayGdwsUserPassword() {
        return upfEasipayGdwsUserPassword;
    }

    public void setUpfEasipayGdwsUserPassword(String upfEasipayGdwsUserPassword) {
        this.upfEasipayGdwsUserPassword = upfEasipayGdwsUserPassword;
    }

    public String getUpfEasipayGdwsUserEncryption() {
        return upfEasipayGdwsUserEncryption;
    }

    public void setUpfEasipayGdwsUserEncryption(String upfEasipayGdwsUserEncryption) {
        this.upfEasipayGdwsUserEncryption = upfEasipayGdwsUserEncryption;
    }
}
