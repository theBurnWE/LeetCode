package com.shcepp.shdippsvr.business.service;

import com.shcepp.shdippsvr.business.exception.MessageException;


public interface CaptchaService {
    /**
     * 发送验证码短消息
     *
     * @param mobilePhoneNo 手机号
     * @param captcha       验证码
     * @throws MessageException
     */
    void sendCaptchaViaSM(String mobilePhoneNo, String captcha, String captchaType) throws MessageException;
    
    /**
     * 检查发送间隔
     *
     * @param ip     ip
     * @param id     id
     * @param idType id类型
     * @return
     */
    boolean checkPeriod(String ip, String id, String idType, String captchaType);
    
    /**
     * 设置发送间隔点
     *
     * @param ip     ip
     * @param id     id
     * @param idType id类型
     * @return
     */
    void setPeriodPoint(String ip, String id, String idType, String captchaType);
    
    /**
     * 发送验证码邮件
     *
     * @param email   邮件地址
     * @param captcha 验证码
     * @throws MessageException
     */
    void sendCaptchaViaEmail(String email, String captcha,  String captchaType) throws MessageException;
    
    /**
     * 生成并保存验证码
     *
     * @param id     登录id
     * @param idType id类型
     * @return
     * @throws MessageException
     */
    String generateAndStoreCaptcha(String id, String idType, String captchaType) throws MessageException;
    
    /**
     * 验证验证码
     *
     * @param id      登录id
     * @param idType  id类型
     * @param captcha 验证码
     * @return
     * @throws MessageException
     */
    boolean verifyCaptcha(String id, String idType, String captcha,  String captchaType) throws MessageException;
}
