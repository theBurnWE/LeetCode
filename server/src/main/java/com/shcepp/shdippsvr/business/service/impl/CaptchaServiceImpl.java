package com.shcepp.shdippsvr.business.service.impl;

import com.shcepp.shdippsvr.business.exception.MessageException;
import com.shcepp.shdippsvr.business.service.CaptchaService;

import com.shcepp.shdippsvr.business.service.MailService;
import com.shcepp.shdippsvr.business.service.SMService;
import com.shcepp.shdippsvr.sys.service.RedisUtil;
import com.shcepp.shdippsvr.sys.util.CaptchaUtil;
import com.shcepp.shdippsvr.sys.util.StringUtils;
import com.shcepp.shdippsvr.sys.util.TemplateUtil;
import kr.pe.kwonnam.slf4jlambda.LambdaLogger;
import kr.pe.kwonnam.slf4jlambda.LambdaLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaptchaServiceImpl implements CaptchaService {
    private final LambdaLogger logger = LambdaLoggerFactory.getLogger(getClass());
    @Autowired
    SMService smService;
    
    @Autowired
    MailService mailService;
    
    
    @Override
    public void sendCaptchaViaSM(String mobilePhoneNo, String captcha, String captchaType) throws MessageException {
        //获得短消息模板
        String content = TemplateUtil.getShortMessageContent(captcha, captchaType);
        //发送短消息
        smService.sendMessage(mobilePhoneNo, content);
    }
    
    private String getPeriodKey(String ip, String id, String idType, String captchaType) {
        return String.format("%s**%s**%s**%s", ip.hashCode(), id.hashCode(), idType, captchaType);
    }
    
    @Override
    public boolean checkPeriod(String ip, String id, String idType, String captchaType) {
        //拼装key
        String key = getPeriodKey(ip, id, idType, captchaType);
        String strPeriod = RedisUtil.get(key);
        return StringUtils.isEmpty(strPeriod);
    }
    
    @Override
    public void setPeriodPoint(String ip, String id, String idType, String captchaType) {
        String key = getPeriodKey(ip, id, idType, captchaType);
        RedisUtil.setEx(key, "ok", 55);
    }

    
    @Override
    public void sendCaptchaViaEmail(String email, String captcha, String captchaType) throws MessageException {
        //获得邮箱发送模板
        String content = TemplateUtil.getEmailContent(captcha, captchaType);
        //发送验证码邮件
        mailService.sendMessage("跨境公服注册验证码", email, content);
    }
    
    @Override
    public String generateAndStoreCaptcha(String id, String idType, String captchaType) throws MessageException {
        //判断idType是手机还是邮箱
        boolean onlyNum = "1".equals(idType) ? true : false;
        //调用CaptchaUtil生成验证码 6位 手机码不含字母，邮箱码含字母
        String captcha = CaptchaUtil.getCharacterAndNumber(6, onlyNum);
        //根据id+**+idType 生成key，保存captcha入redis
        String redisKey = String.format("%s**%s**%s", id.hashCode(), idType, captchaType);
        RedisUtil.setCaptcha(redisKey, captcha);
        logger.debug("generated captcha: {},stored to redis and key: {}", () -> captcha, () -> redisKey);
        //返回captcha
        return captcha;
    }
    
    @Override
    public boolean verifyCaptcha(String id, String idType, String captcha, String captchaType) throws MessageException {
        //根据id+**+idType 生成key
        String redisKey = String.format("%s**%s**%s", id.hashCode(), idType, captchaType);
        // 获取redis中的captcha
        String serverCaptcha = RedisUtil.get(redisKey);
        // 比较captcha一致性
        logger.debug("matching captcha, id:{} , idType:{} , clientCaptcha: {}, serverCaptcha: {}, captchaType: {}", () -> id, () -> idType, () -> captcha, () -> serverCaptcha, () -> captchaType);
        //返回比较结果
        boolean ret = !StringUtils.isBlank(serverCaptcha) && serverCaptcha.equals(captcha);
        // added by zkmao
        // 20190221
        // if verify ok, remove captcha;
        if(ret) {
            RedisUtil.removeCaptcha(redisKey);
        }
        return ret;
        
        
    }
}
