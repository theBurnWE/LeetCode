package com.shcepp.shdippsvr.oauth.service;

import com.shcepp.shdippsvr.business.config.UserConfig;
import com.shcepp.shdippsvr.business.service.UserRegisterService;
import com.shcepp.shdippsvr.oauth.dto.*;
import com.shcepp.shdippsvr.oauth.util.DESEncryptUtils;
import com.shcepp.shdippsvr.sys.service.BaseService;
import com.shcepp.shdippsvr.sys.service.RedisUtil;
import com.shcepp.shdippsvr.sys.util.Constants;
import com.shcepp.shdippsvr.sys.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by zwu on 2016/11/28.
 */
@Service("appOAuthService")
public class AppOAuthService extends BaseService {

    private static final Logger logger = LoggerFactory.getLogger(AppOAuthService.class);
    @Autowired
    UserRegisterService userRegisterService;

    private String urlEncodedForm(String url, Map requestMap) {
        StringBuilder res = new StringBuilder();

        Iterator<Map.Entry<String, String>> it = requestMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            res.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }

        res.insert(0, "?").insert(0, url);
        int len = res.length();

        return res.substring(0, len - 1);
    }

    public OAuthReturnInfo getRefreshTokenWithCaptcha(String loginId, String password, String clientId, String appCode) throws Exception {
        String url = UserConfig.EPOA_URL
                + "/oauth/AuthAutoWithoutCaptcha";// get refresh_token url
        HashMap<String, String> params = new HashMap<>();
        params.put("id", loginId);
        params.put("password", password);
        params.put("client_id", clientId);
        params.put("app_code", appCode);
        OAuthReturnInfo ori = null;
        try {
            String ret = restTemplate.getForObject(urlEncodedForm(url, params), String.class);
            logger.info(ret);
            ori = JsonUtil.jsonToBean(ret, OAuthReturnInfo.class);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return ori;
    }

    /**
     * token调用
     *
     * @param url
     * @param params
     * @return
     */
    private ResultInfo getAccessToken(String url, Map<String, String> params) {
        String client_id = UserConfig.CLIENT_ID;
        params.put("client_id", client_id);
        ResultInfo ri = new ResultInfo();
        try {
            String ret = restTemplate.getForObject(urlEncodedForm(url, params), String.class);
            logger.info(ret);
            OAuthReturnInfo ori = JsonUtil.jsonToBean(ret, OAuthReturnInfo.class);
            if ("T".equals(ori.getFlag()) && !StringUtils.isEmpty(ori.getRefreshToken())) {
                ri.resultFlag = "1";
                ri.resultInfo = ori.getAccessToken();
            } else {
                ri.resultFlag = "0";
                ri.errorInfo = "";
            }
        } catch (Exception e) {
            logger.error("error", e);
        }
        return ri;
    }

    /**
     * 有token用户调用此方法
     *
     * @param refreshToken
     * @param appCode
     * @return ResultInfo
     */
    public ResultInfo getAccessToken(String refreshToken, String appCode) {
        String url = UserConfig.EPOA_URL + "/oauth/accesstoken";
        
        HashMap<String, String> params = new HashMap<>();
        params.put("refresh_token", refreshToken);
        if (!StringUtils.isEmpty(appCode)) {
            params.put("app_code", appCode);
        }
        return this.getAccessToken(url, params);
    }

//    /**
//     * 无token调用此方法
//     *
//     * @return token
//     */
//    public String getAccessToken() {
//        String result = "";
//        String url = RedisUtil.getConfig("RegisterAccesstokenUrl");
//        String author_code = RedisUtil.getConfig("RegisterAuthorCode");// get access_token url
//        String app_code = "TRANSINF";
//
//        HashMap<String, String> params = new HashMap<>();
//        params.put("author_code", author_code);
//        // IMGD必须带，其他应用清除
//        if (!StringUtils.isEmpty(app_code)) {
//            params.put("app_code", app_code);
//        }
//        ResultInfo info = this.getAccessToken(url, params);
//
//        return info.resultInfo;
//    }

//    public CardidInfo getICcardidInfo(String refreshToken, String appCode) {
//        CardidInfo ci = null;
//        ResultInfo ri = this.getAccessToken(refreshToken, appCode);
//        if ("1".equals(ri.resultFlag)) {
//            String accessToken = ri.resultInfo;
//            String url = RedisUtil.getConfig("cardidInfoUrl"); //cardidInfo url
//            String client_id = RedisUtil.getConfig("ServiceURL");// 应用自己的service
//            HashMap<String, String> params = new HashMap<>();
//            params.put("access_token", accessToken);
//            params.put("client_id", client_id);
//            try {
//                ci = restTemplate.postForObject(url, params, CardidInfo.class);
//            } catch (Exception e) {
//                logger.error("error", e);
//            }
//        }
//        return ci;
//    }

    /**
     * 通过refreshToken获取用户信息
     *
     * @param refreshToken
     * @param appCode
     * @return
     */
    public OAuthUserInfo getUserInfo(String refreshToken, String appCode) {
        OAuthUserInfo ori = null;
        String userInfo = RedisUtil.getToken(refreshToken);
        if (null != userInfo) {
            ori = JsonUtil.jsonToBean(userInfo, OAuthUserInfo.class);
        }

        if (null == ori) {
            ResultInfo ri = this.getAccessToken(refreshToken, appCode);
            if ("1".equals(ri.resultFlag)) {
                String accessToken = ri.resultInfo;
                String url = UserConfig.EPOA_URL
                        + "/openapi/userinfo"; //token url
                String client_id = UserConfig.CLIENT_ID;

                HashMap<String, String> params = new HashMap<>();
                params.put("access_token", accessToken);
                params.put("client_id", client_id);
                try {
                    String ret = restTemplate.getForObject(urlEncodedForm(url, params), String.class);
                    logger.info(ret);
                    ori = JsonUtil.jsonToBean(ret, OAuthUserInfo.class);
                    if ("T".equals(ori.flag)){
                        userRegisterService.DealWithUserInfo(ori);
                    }
                } catch (Exception e) {
                    logger.error("error", e);
                }
            } else {
                logger.error("获取用户信息失败！");
            }
        }
        return ori;
    }

//    public OAuthResponse checkrt(String refreshToken) {
//        OAuthResponse ori = null;
//        String ret = "";
//        String url = RedisUtil.getConfig("winxportal.checkrt"); //token url
//        String client_id = RedisUtil.getConfig("ServiceURL");// 应用自己的service
//        HashMap<String, String> params = new HashMap<>();
//        params.put("refresh_token", refreshToken);
//        params.put("client_id", client_id);
//        try {
//            ret = restTemplate.getForObject(urlEncodedForm(url, params), String.class);
//            ori = JsonUtil.jsonToBean(ret, OAuthResponse.class);
//        } catch (Exception e) {
//            logger.error("error", e);
//        }
//
//        return ori;
//    }

//    /**
//     * 验证手机是否存在
//     *
//     * @param serviceURL
//     * @param accessToken
//     * @param phone
//     * @return
//     * @throws Exception
//     */
//    private boolean checkPhone(String serviceURL, String accessToken, String phone)
//            throws Exception {
//        boolean result = false;
//        String url = RedisUtil.getConfig("RegisterCheckphoneUrl");
//        HashMap<String, String> params = new HashMap<>();
//        params.put("access_token", accessToken);
//        params.put("client_id", serviceURL);
//        params.put("phone", phone);
//
//        // 验证参数
//        if (serviceURL == "" && accessToken == "" && phone == ""
//                && !phone.matches(Constants.MOBILE_VERIFY_REGEX)) {
//            logger.error("验证手机号异常:必要参数为空!");
//            throw new Exception("验证手机号异常!");
//        }
//        try {
//            String ret = restTemplate.getForObject(urlEncodedForm(url, params), String.class);
//            logger.info(ret);
//            OAuthReturnInfo ori = JsonUtil.jsonToBean(ret, OAuthReturnInfo.class);
//            if ("F".equals(ori.getFlag())) {
//                if (!ori.getErrorCode().isEmpty()
//                        && "phoneNumNotFound".equals(ori.getErrorCode())) {
//                    result = true;
//                } else {
//                    logger.error("验证手机号异常:" + ori.getErrorInfo()
//                                         + ";errorCode=" + ori.getErrorCode());
//                    throw new Exception("验证手机号异常!");
//                }
//            }
//        } catch (Exception e) {
//            logger.error("error", e);
//        }
//        return result;
//    }
//
//    /**
//     * 验证手机是否存在（对外暴露）
//     *
//     * @param phone
//     * @throws Exception
//     */
//    public boolean checkPhone(String phone) throws Exception {
//        if (!this.checkPhone(RedisUtil.getConfig("ServiceURL"),
//                             this.getAccessToken(), phone)) {
//            logger.info("该手机号已被注册!");
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    /**
//     * 新用户注册(边检)
//     * 抛错在controller层接收
//     *
//     * @param params
//     * @throws Exception
//     */
//    public void saveOrUpdateUser(Map params) throws Exception {
//        String url = RedisUtil.getConfig("RegisterUserUrl");
//        String access_token = this.getAccessToken();
//        params.put("client_id", RedisUtil.getConfig("bjServiceUrl"));
//        params.put("roleCode", RedisUtil.getConfig("bjWechatRole"));
//        params.put("orgCode", RedisUtil.getConfig("bjOrgCode"));
//        params.put("access_token", access_token);
//        params.put("password", DESEncryptUtils.oauthLoginEncode((String) params.get("password")));
//        try {
//            String ret = restTemplate.getForObject(urlEncodedForm(url, params), String.class);
//            logger.info(ret);
//            OAuthReturnInfo oti = JsonUtil.jsonToBean(ret, OAuthReturnInfo.class);
//            if ("F".equals(oti.getFlag())) {
//                throw new Exception(oti.getErrorInfo());
//            }
//        } catch (Exception e) {
//            logger.error("error", e);
//            throw new Exception(e.getMessage());
//        }
//    }

//    /**
//     * 通过手机重置密码
//     * 抛错在controller层接收
//     *
//     * @param params
//     * @throws Exception
//     */
//    public void resetUserPass(Map params) throws Exception {
//        String url = RedisUtil.getConfig("ResetUserPassUrl");
//        String client_id = RedisUtil.getConfig("ServiceURL");
//        String access_token = this.getAccessToken();
//        params.put("client_id", client_id);
//        params.put("access_token", access_token);
//        params.put("newPassword", DESEncryptUtils.oauthLoginEncode((String) params.get("newPassword")));
//        try {
//            String ret = restTemplate.getForObject(urlEncodedForm(url, params), String.class);
//            logger.info(ret);
//            OAuthReturnInfo oti = JsonUtil.jsonToBean(ret, OAuthReturnInfo.class);
//            if ("F".equals(oti.getFlag())) {
//                throw new Exception(oti.getErrorInfo());
//            }
//        } catch (Exception e) {
//            logger.error("error", e);
//            throw new Exception(e.getMessage());
//        }
//    }

    /**
     * 通过refreshToken注销
     *
     * @param refreshToken
     * @param appCode
     * @return
     */
    public OAuthResponse kickout(String refreshToken, String appCode) {
        OAuthResponse ori = null;
        String ret = "";
        String url = UserConfig.EPOA_URL
                + "/oauth/logout"; //token url
        String client_id = UserConfig.CLIENT_ID;
        HashMap<String, String> params = new HashMap<>();
        params.put("refresh_token", refreshToken);
        params.put("client_id", client_id);
        params.put("app_code", appCode);
        try {
            ret = restTemplate.getForObject(urlEncodedForm(url, params), String.class);
            logger.info(ret);
            ori = JsonUtil.jsonToBean(ret, OAuthResponse.class);
        } catch (Exception e) {
            logger.error("error", e);
        }
        return ori;
    }

//    public OAuthResetPwdInfo resetPass(String refreshToken, String oldPass, String newPass, String userName) {
//        OAuthResetPwdInfo ori = null;
//        String ret = "";
//        ResultInfo rtInfo = getAccessToken(refreshToken, "TRANSINF");
//        if (null != rtInfo && "1".equals(rtInfo.resultFlag)) {
//            String url = RedisUtil.getConfig("EpoaService_back_url")
//                    + "uthweb/updatePwd";
//            String client_id = RedisUtil.getConfig("ServiceURL");// 应用自己的service
//            HashMap<String, String> params = new HashMap<>();
//            params.put("client_id", client_id);
//            params.put("access_token", rtInfo.resultInfo);
//            params.put("userName", userName);
//            params.put("oldPassword", oldPass);
//            params.put("newPassword", newPass);
//            try {
//                ret = restTemplate.getForObject(urlEncodedForm(url, params), String.class);
//                logger.info(ret);
//                ori = JsonUtil.jsonToBean(ret, OAuthResetPwdInfo.class);
//            } catch (Exception e) {
//                logger.error("error", e);
//            }
//        } else {
//            logger.error(JsonUtil.beanToJson(rtInfo));
//        }
//        return ori;
//    }

}
