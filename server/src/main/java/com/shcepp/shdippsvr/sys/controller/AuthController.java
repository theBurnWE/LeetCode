package com.shcepp.shdippsvr.sys.controller;

import com.netflix.ribbon.proxy.annotation.Http;
import com.shcepp.shdippsvr.business.dao.BaseIpInLogDao;
import com.shcepp.shdippsvr.business.dao.ShdippBizUserDao;
import com.shcepp.shdippsvr.business.entity.AppUserEntity;
import com.shcepp.shdippsvr.business.entity.BaseIpInLogEntity;
import com.shcepp.shdippsvr.business.entity.ShdippBizUserEntity;
import com.shcepp.shdippsvr.business.service.UserRegisterService;
import com.shcepp.shdippsvr.oauth.dto.OAuthResetPwdInfo;
import com.shcepp.shdippsvr.oauth.dto.OAuthUserInfo;
import com.shcepp.shdippsvr.oauth.service.AppOAuthService;
import com.shcepp.shdippsvr.sys.exception.EasiControllerException;
import com.shcepp.shdippsvr.sys.util.ApiResult;
import com.shcepp.shdippsvr.sys.util.Constants;
import com.shcepp.shdippsvr.sys.util.JsonUtil;
import com.shcepp.shdippsvr.sys.util.StringUtils;
import kr.pe.kwonnam.slf4jlambda.LambdaLogger;
import kr.pe.kwonnam.slf4jlambda.LambdaLoggerFactory;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Merjiezo on 2018/2/27.
 */
@RestController
@RequestMapping("/user")
public class AuthController {
    
    private final LambdaLogger logger = LambdaLoggerFactory.getLogger(getClass());

    @Autowired
    UserRegisterService userRegisterService;
    
    @Autowired
    private BaseIpInLogDao baseIpInLogDao;
    
    @PostMapping("/userInfo")
    public ApiResult userInfoWithAuth(@RequestHeader String token, HttpServletRequest request, OAuthUserInfo oui) {
        logger.info("-------------------获取用户信息--------------------------");
        ApiResult apiE;
        Map<String, Object> result = new HashMap<>();
        List<AppUserEntity> apps = null;
        try {
            result.put("userInfo", arrangeUserInfo(oui));
            apiE = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_T, "", "调用成功", result);
        }
        catch(Exception e) {
            logger.error("错误", e);
            apiE = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_F, "", "获取用户信息失败！", null);
        }
        return apiE;
    }
    @PostMapping("/userInfo1")
    public ApiResult userInfo1(@RequestHeader String epToken, HttpServletRequest request, OAuthUserInfo oui) {
        logger.info("-------------------获取用户信息--------------------------");
        ApiResult apiE;
        Map<String, Object> result = new HashMap<>();
        List<AppUserEntity> apps = null;
        try {
            result.put("userInfo", arrangeUserInfo(oui));
            apiE = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_T, "", "调用成功", result);
        }
        catch(Exception e) {
            logger.error("错误", e);
            apiE = ApiResult.newInstance(Constants.CLIENT_FLAG.FLAG_F, "", "获取用户信息失败！", null);
        }
        return apiE;
    }

    /***
     * 整理用户
     * @return
     */
    private Map<String, Object> arrangeUserInfo(OAuthUserInfo oui) {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("orgName", MapUtils.getString(oui.info.attrs, "Orgname", ""));
        userInfo.put("orgCode", MapUtils.getString(oui.info.attrs, "OrgorgCode", ""));
        userInfo.put("userLoginId", MapUtils.getString(oui.info.attrs, "UserloginId", ""));
        userInfo.put("username", MapUtils.getString(oui.info.attrs, "Username", ""));
        userInfo.put("userTele", MapUtils.getString(oui.info.attrs, "Usertele", ""));
        userInfo.put("userMail", MapUtils.getString(oui.info.attrs, "Usermail", ""));
        userInfo.put("userId", MapUtils.getString(oui.info.attrs, "ShdippUserid", ""));
        
       
        List<BaseIpInLogEntity> items = baseIpInLogDao.getFirst2BySptOrderByCreateTimeDesc(
                MapUtils.getString(oui.info.attrs, "UserloginId", ""));
        if(null != items && items.size() > 0) {
            userInfo.put("lastIp", items.get(0));
            if(items.size() > 1) {
                userInfo.put("lastLogin", items.get(1));
            }
        }
        List roleList = new ArrayList<>();
        List<OAuthUserInfo.UserInfo.UmRole> userRoles = oui.info.roles;
        for(OAuthUserInfo.UserInfo.UmRole umRole : userRoles) {
            roleList.add(umRole.roleCode);
        }
        userInfo.put("roles", roleList);
        return userInfo;
    }

}
