package com.shcepp.shdippsvr.sys.controller;

import com.shcepp.shdippsvr.business.config.UserConfig;
import com.shcepp.shdippsvr.business.service.IpLimitService;
import com.shcepp.shdippsvr.oauth.dto.OAuthUserInfo;
import com.shcepp.shdippsvr.oauth.service.AppOAuthService;
import com.shcepp.shdippsvr.sys.exception.EasiControllerException;
import com.shcepp.shdippsvr.sys.exception.EasiServiceException;
import com.shcepp.shdippsvr.sys.service.ApiService;
import com.shcepp.shdippsvr.sys.service.RedisUtil;
import com.shcepp.shdippsvr.sys.util.ApiResult;
import com.shcepp.shdippsvr.sys.util.Constants;
import com.shcepp.shdippsvr.sys.util.IpUtil;
import com.shcepp.shdippsvr.sys.util.JsonUtil;
import kr.pe.kwonnam.slf4jlambda.LambdaLogger;
import kr.pe.kwonnam.slf4jlambda.LambdaLoggerFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Merjiezo on 2017/8/8.
 */
@RestController
@RequestMapping("/user")
public class ApiController {
    
    private final LambdaLogger log = LambdaLoggerFactory.getLogger(getClass());

    @Resource(name = "apiService")
    private ApiService apiService;
    @Resource(name = "appOAuthService")
    public AppOAuthService appOAuthService;

    @Autowired
    RedisUtil redisUtil;
    
    @Autowired
    IpLimitService ipLimitService;

    @PostMapping("/login")
    public ApiResult Login(String id, String password, String captchaVal, String captchaUUid,
                           HttpServletRequest request,
                           HttpServletResponse response)
            throws Exception {
        log.info("-------------------login start--------------------------");
        ApiResult result;
        String captchaRedisVal;
        String ip = IpUtil.getIp(request);

//        if(StringUtil.isBlank(ip)) {
//            ip = "null";
//        }
        log.info("访问用户ip:{}", ip);
    
        try {
        
        
            boolean isLimited = ipLimitService.checkIpCount(ip);
        
            if(isLimited) {
            
                // 1为校验，方便开发
                if("1".equals(UserConfig.CAPTCHA_SWITCH)) {
//                String captchaVal = map.get("captchaVal");
//                String captchaUUid = map.get("captchaUUid");
                    if(null != captchaVal && null != captchaUUid) {
                        captchaRedisVal = RedisUtil.get("Captcha_" + captchaUUid);
                        if(null != captchaRedisVal) {
                            captchaRedisVal = captchaRedisVal.toUpperCase();
                            if(captchaRedisVal.equals(captchaVal.toUpperCase())) {
                                RedisUtil.remove("Captcha_" + captchaUUid);
                                Map<String, String> dataMap = apiService.handleLogin(id, password);
                                String rt = dataMap.get("rt");
                                if(StringUtils.isNotEmpty(rt)) {
                                    OAuthUserInfo oui = appOAuthService.getUserInfo(rt, Constants.APP_CODE);
                                    if("T".equals(oui.flag)) {
                                        String loginId = oui.info.attrs.get("UserloginId");
                                        ipLimitService.saveIpLog(ip, loginId);
                                    }
                                    else {
                                        ipLimitService.saveIpLog(ip, "null");
                                    }
                                }
                                else {
                                    ipLimitService.saveIpLog(ip, "null");
                                }
                                result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_T,
                                        null, null, dataMap);
                                log.info(JsonUtil.beanToJson(result));
                            }
                            else {
                                ipLimitService.saveIpLog(ip, "null");
                                result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_F,
                                        Constants.CLIENT_ERROR_000.ERROR_CODE001, "验证码不正确", null);
                                log.error(JsonUtil.beanToJson(result));
                            }
                        }
                        else {
                            ipLimitService.saveIpLog(ip, "null");
                            result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_F,
                                    Constants.CLIENT_ERROR_000.ERROR_CODE001, "验证码不存在或过期", null);
                            log.error(JsonUtil.beanToJson(result));
                        }
                    }
                    else {
                        ipLimitService.saveIpLog(ip, "null");
                        throw new EasiControllerException(Constants.CLIENT_ERROR_100.ERROR_CODE101, Constants.CLIENT_ERROR_100.ERROR_INFO101);
                    }
                }
                else {
                    Map<String, String> dataMap = apiService.handleLogin(id, password);
                
                    result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_T,
                            null, null, dataMap);
                    log.info(JsonUtil.beanToJson(result));
                }
            }
            else {
                ipLimitService.saveIpLog(ip, "null");
                result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_F,
                        Constants.CLIENT_ERROR_900.ERROR_CODE909, Constants.CLIENT_ERROR_900.ERROR_INFO909, null);
                log.info(JsonUtil.beanToJson(result));
            }
        }
        catch(EasiServiceException ese) {
            ipLimitService.saveIpLog(ip, "null");
            result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_F,
                    ese.getStatus(), ese.getMessage(), null);
            log.error(JsonUtil.beanToJson(result));
        }
        return result;
    }

    //登出
    @PostMapping("/logout")
    public ApiResult loginOut(HttpServletRequest request, String refreshToken) {
        log.debug("-------------------loginOut start--------------------------");
        ApiResult result;

        try {
            apiService.handleLogout(refreshToken);
            result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_T,
                    null, null, "注销成功！");
            log.info(JsonUtil.beanToJson(result));
        } catch (EasiServiceException ese) {
            result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_F,
                    ese.getStatus(), ese.getMessage(), null);
            log.error(JsonUtil.beanToJson(result));
        }
        return result;
    }

//    /***
//     * 获取配置项
//     * @return
//     */
//    @PostMapping(value = "getConfig")
//    public ApiResult getConfig (@RequestBody Map<String,Object> map) {
//        ApiResult result;
//        log.info("---------------获取配置项");
//        Map<String, Object> dataMap = new HashMap<String, Object>();
//        List data =  (List) map.get("configKey");
//        if (null == data) {
//            result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_T, "", "", "");
//            log.info(JsonUtil.beanToJson(result));
//            return result;
//        }
//        for (Object key: data) {
//            String keyStr = (String) key;
//            dataMap.put(keyStr, RedisUtil.getConfig(keyStr));
//        }
//        result = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_T, "", "", dataMap);
//        log.info(JsonUtil.beanToJson(result));
//        return result;
//    }


}
