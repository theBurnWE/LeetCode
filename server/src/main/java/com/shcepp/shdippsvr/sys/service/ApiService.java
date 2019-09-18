package com.shcepp.shdippsvr.sys.service;

import com.shcepp.shdippsvr.business.config.UserConfig;
import com.shcepp.shdippsvr.business.service.UserRegisterService;
import com.shcepp.shdippsvr.oauth.dto.OAuthResponse;
import com.shcepp.shdippsvr.oauth.dto.OAuthReturnInfo;
import com.shcepp.shdippsvr.oauth.dto.OAuthUserInfo;
import com.shcepp.shdippsvr.oauth.service.AppOAuthService;
import com.shcepp.shdippsvr.oauth.util.DESEncryptUtils;
import com.shcepp.shdippsvr.sys.exception.EasiServiceException;
import com.shcepp.shdippsvr.sys.util.Constants;
import com.shcepp.shdippsvr.sys.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/***
 * Created by Merjiezo on 2017/7/24.
 */
@Service("apiService")
public class ApiService extends BaseService {

    @Resource(name = "appOAuthService")
    AppOAuthService appOAuthService;
    @Autowired
    UserRegisterService userRegisterService;

    private final static String APP_CODE = Constants.APP_CODE;
    
    /***
     * 登陆
     * @param
     * @return
     * @throws Exception
     */
    public Map<String, String> handleLogin (String id, String password) throws Exception {
        Map<String, String> res = new HashMap<>();
        
        String enLoginId = DESEncryptUtils.encode(id);
        String enPass = DESEncryptUtils.encode(password);
        String enClient = DESEncryptUtils.encode(UserConfig.CLIENT_ID);
        String enAppCode = DESEncryptUtils.encode(APP_CODE);
        OAuthReturnInfo ri = appOAuthService.getRefreshTokenWithCaptcha(enLoginId, enPass, enClient, enAppCode);
        
        if (null == ri) {
            throw new EasiServiceException(Constants.CLIENT_ERROR_900.ERROR_CODE907, Constants.CLIENT_ERROR_900.ERROR_INFO907);
        }
        
        if ("T".equals(ri.getFlag())) {
            OAuthUserInfo oui = appOAuthService.getUserInfo(ri.getRefreshToken(), APP_CODE);
            if (null == oui) {
                throw new EasiServiceException(Constants.CLIENT_ERROR_900.ERROR_CODE907, Constants.CLIENT_ERROR_900.ERROR_INFO907);
            }
            if ("T".equals(oui.flag)) {
                userRegisterService.DealWithUserInfo(oui);
                //用户信息放入Radis
                RedisUtil.setToken(ri.getRefreshToken(), oui);
                res.put("rt", ri.getRefreshToken());
                return res;
            } else {
                throw new EasiServiceException(Constants.CLIENT_ERROR_900.ERROR_CODE908,
                        oui.errorInfo);
            }
        } else {
            throw new EasiServiceException(Constants.CLIENT_ERROR_900.ERROR_CODE908,
                    ri.getErrorInfo());
        }
    }
    
    /***
     * 登出
     * @param token
     */
    public void handleLogout (String token) {
        OAuthResponse oar = appOAuthService.kickout(token, APP_CODE);
        if (null == oar) {
            throw new EasiServiceException(Constants.CLIENT_ERROR_900.ERROR_CODE907, Constants.CLIENT_ERROR_900.ERROR_INFO907);
        }
        
        if ("F".equals(oar.flag)) {
            if ("RefreshTokenNoFind".equals(oar.errorCode)) {
                RedisUtil.removeToken(token);
            } else {
                throw new EasiServiceException(Constants.CLIENT_ERROR_900.ERROR_CODE908,
                        oar.errorInfo);
            }
        } else {
            RedisUtil.removeToken(token);
        }
    }
    
    /**
     * 获取用户信息
     * @param token
     * @return
     */
    public OAuthUserInfo getOauth(String token) {
        String res = RedisUtil.get(token);
        if (res != null) {
            String userInfoStr = RedisUtil.get(Constants.REDIS_USERID + res);
            if (userInfoStr != null) {
                return JsonUtil.jsonToBean(userInfoStr, OAuthUserInfo.class);
            }
        }
        return null;
    }

}
