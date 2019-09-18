package com.shcepp.shdippsvr.business.controller;

import com.shcepp.shdippsvr.business.bean.http.DefaultResponse;
import com.shcepp.shdippsvr.business.exception.MessageException;
import com.shcepp.shdippsvr.business.service.CaptchaService;
import com.shcepp.shdippsvr.business.service.UserRegisterService;
import com.shcepp.shdippsvr.oauth.dto.OAuthUserInfo;
import com.shcepp.shdippsvr.sys.controller.BaseController;
import com.shcepp.shdippsvr.sys.service.RedisUtil;
import com.shcepp.shdippsvr.sys.util.ApiResult;
import com.shcepp.shdippsvr.sys.util.Constants;
import com.shcepp.shdippsvr.sys.util.IpUtil;
import com.shcepp.shdippsvr.sys.util.StringUtils;
import kr.pe.kwonnam.slf4jlambda.LambdaLogger;
import kr.pe.kwonnam.slf4jlambda.LambdaLoggerFactory;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zkmao
 * @description
 * @date 2019/8/22 9:46
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    private final LambdaLogger logger = LambdaLoggerFactory.getLogger(getClass());
    
    @Autowired
    UserRegisterService userRegisterService;
    
    @Autowired
    CaptchaService captchaService;
    
    
    /**
     * 检查id是否存在
     *
     * @param id     用户注册id
     * @param idType 用户id类型
     * @return
     */
    @RequestMapping(value = "/checkId", method = RequestMethod.POST)
    public ApiResult checkId(String id, String idType) {
        logger.info("Processing checkId, request param id: {} and idType: {}", () -> id, () -> idType);
        ApiResult result = null;
        DefaultResponse res = new DefaultResponse();
        res.rtnCode = "";
        res.rtnInfo = "";
        
        try {
            int ret = userRegisterService.checkUserExists(id, idType);
            
            res.rtnCode = String.format("%s", ret);
            res.rtnInfo = (1 == ret) ? "账号存在" : "账号不存在";
            result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_T, "", "调用成功", res);
        }
        catch(MessageException ex) {
            result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_F, ex.getMsgCode(), ex.getMessage(), null);
        }
        
        return result;
    }
    
    /**
     * 发送验证码
     *
     * @param id     用户注册id
     * @param idType 用户id类型
     * @return
     */
    @RequestMapping(value = "/sendCaptcha", method = RequestMethod.POST)
    public ApiResult sendCaptcha(String id, String idType, HttpServletRequest request) {
        logger.info("Processing sendCaptcha, request param id: {} and idType: {}", () -> id, () -> idType);
        ApiResult result = null;
        DefaultResponse res = new DefaultResponse();
        res.rtnCode = "";
        res.rtnInfo = "";
        
        //发送验证码
        try {
            String ip = IpUtil.getIp(request);
            
            
            logger.info("访问用户ip:{}", ip);
            
            userRegisterService.sendCaptcha(id, idType, ip);
            res.rtnCode = "1";
            res.rtnInfo = "发送成功";
            result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_T, "", "调用成功", res);
        }
        catch(MessageException ex) {
            result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_F, ex.getMsgCode(), ex.getMessage(), null);
        }
        return result;
    }
    
    
    /**
     * 注册
     *
     * @param loginId  登录名
     * @param mobile 手机号
     * @param mail 邮箱
     * @param captcha  验证码
     * @param password 密码
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ApiResult register(String loginId, String mobile, String mail, String captcha, String password, String captchaUUid) {
        logger.info("Processing register, request param loginId: {}, mobile: {}, mail: {}, captcha: {}", loginId, mobile, mail, captcha);
        ApiResult result = null;
        DefaultResponse res = new DefaultResponse();
        res.rtnCode = "";
        res.rtnInfo = "";
        
        try {
            String id = "";
            String idType = "";
            //如果手机号不为空，则说明是以手机号发送验证码
            if (!StringUtils.isBlank(mobile)){
                id = mobile;
                idType = "1";
            }
            else if (!StringUtils.isBlank(mail)) {
                id = mail;
                idType = "2";
            }
            else {
                throw new MessageException(
                        Constants.USER_REG_ERROR.ERROR_CODE011,
                        Constants.USER_REG_ERROR.ERROR_INFO011);
            }
            boolean captchaVerified = captchaService.verifyCaptcha(id, idType, captcha, Constants.CAPTCHA_REG_TYPE);
            if(captchaVerified) {
                if(userRegisterService.registerUser(loginId, mobile, mail, password)) {
                    res.rtnCode = "1";
                    //modified by zkmao
                    //20190301
                    //为了注册成功后直接登录进行修改
                    //注册成功后，根据客户端传来的captchaUUid，获取当前登录验证码，并返回
                    String captchaRedisVal = RedisUtil.get("Captcha_" + captchaUUid);
                    if(null != captchaRedisVal) {
                        res.rtnInfo = captchaRedisVal;
                    }
                    else {
                        res.rtnInfo = "";
                    }
                    
                }
                else {
                    res.rtnCode = "0";
                    res.rtnInfo = "注册失败";
                }
                result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_T, "", "调用成功", res);
            }
            else {
                throw new MessageException(
                        Constants.USER_REG_ERROR.ERROR_CODE004,
                        Constants.USER_REG_ERROR.ERROR_INFO004);
            }
        }
        catch(MessageException ex) {
            
            result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_F, ex.getMsgCode(), ex.getMessage(), null);
        }
        return result;
    }
    
    
    /**
     * 修改密码
     *
     * @param token 认证凭据
     * @param oldPwd  旧密码
     * @param newPwd 新密码
     * @return
     */
    @RequestMapping(value = "/changePwd", method = RequestMethod.POST)
    public ApiResult changePwdWithAuth(@RequestHeader String token, String oldPwd, String newPwd,  OAuthUserInfo oui) {
        logger.info("Processing changePwd");
        ApiResult result = null;
        DefaultResponse res = new DefaultResponse();
        res.rtnCode = "";
        res.rtnInfo = "";
        try {
            
            //获取用户登录账户id
            String userLoginId = MapUtils.getString(oui.info.attrs, "UserloginId", "");
            if (StringUtils.isEmpty(userLoginId)) {
                throw new MessageException(
                        Constants.USER_REG_ERROR.ERROR_CODE012,
                        Constants.USER_REG_ERROR.ERROR_INFO012);
            }
            //修改用户密码
            userRegisterService.changePwd(userLoginId, oldPwd, newPwd);
            res.rtnCode = "1";
            res.rtnInfo = "修改成功";
            
            //返回信息
            result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_T, "", "调用成功", res);
            
        }
        catch(MessageException ex) {
            
            result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_F, ex.getMsgCode(), ex.getMessage(), null);
        }
        return result;
    }
    
    /**
     * 发送重置密码验证码
     *
     * @param id     用户注册id
     * @param idType 用户id类型
     * @return
     */
    @RequestMapping(value = "/sendResetPwdCaptcha", method = RequestMethod.POST)
    public ApiResult sendResetPwdCaptcha(String id, String idType, HttpServletRequest request) {
        logger.info("Processing sendResetPwdCaptcha, request param id: {} and idType: {}", () -> id, () -> idType);
        ApiResult result = null;
        DefaultResponse res = new DefaultResponse();
        res.rtnCode = "";
        res.rtnInfo = "";
        
        //发送验证码
        try {
            String ip = IpUtil.getIp(request);
            
            
            logger.info("访问用户ip:{}", ip);
            
            userRegisterService.sendResetPwdCaptcha(id, idType, ip);
            res.rtnCode = "1";
            res.rtnInfo = "发送成功";
            result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_T, "", "调用成功", res);
        }
        catch(MessageException ex) {
            result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_F, ex.getMsgCode(), ex.getMessage(), null);
        }
        return result;
    }
    
    
    /**
     * 重置密码
     *
     * @param id     用户注册id
     * @param idType 用户id类型
     * @param newPwd 新密码
     * @return
     */
    @RequestMapping(value = "/resetPwd", method = RequestMethod.POST)
    public ApiResult resetPwd(String id, String idType,String captcha, String newPwd) {
        logger.info("Processing resetPwd, request param id: {}, idType: {}, captcha: {}", id, idType, captcha);
        ApiResult result = null;
        DefaultResponse res = new DefaultResponse();
        res.rtnCode = "";
        res.rtnInfo = "";
        
        try {
    
            //重置密码
            boolean captchaVerified = captchaService.verifyCaptcha(id, idType, captcha, Constants.CAPTCHA_RESET_TYPE);
            if(captchaVerified) {
                userRegisterService.resetPwd(id, idType, newPwd);
                res.rtnCode = "1";
                res.rtnInfo = "修改成功";
    
            }
            else {
                res.rtnCode = "0";
                res.rtnInfo = "修改失败";
            }
            result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_T, "", "调用成功", res);
    
        }
        catch(MessageException ex) {
            
            result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_F, ex.getMsgCode(), ex.getMessage(), null);
        }
        return result;
    }
    
    
}
