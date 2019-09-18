package com.shcepp.shdippsvr.business.controller;

import com.shcepp.shdippsvr.business.bean.http.DefaultResponse;
import com.shcepp.shdippsvr.business.bean.pojo.resources.CompanyInfoReqPojo;
import com.shcepp.shdippsvr.business.bean.pojo.resources.CompanyInfoRspPojo;
import com.shcepp.shdippsvr.business.service.BaseService;
import com.shcepp.shdippsvr.business.service.CaptchaService;
import com.shcepp.shdippsvr.business.service.CompanyService;
import com.shcepp.shdippsvr.business.service.UserRegisterService;
import com.shcepp.shdippsvr.oauth.dto.OAuthUserInfo;
import com.shcepp.shdippsvr.sys.controller.BaseController;
import com.shcepp.shdippsvr.sys.util.ApiResult;
import kr.pe.kwonnam.slf4jlambda.LambdaLogger;
import kr.pe.kwonnam.slf4jlambda.LambdaLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.shcepp.shdippsvr.business.service.BaseService.FLAG_F;

/**
 * @author IvanXu
 * @description
 * @date 2019/8/27 9:46
 */
@RestController
@RequestMapping("/company")
public class CompanyController extends BaseController {
    private final LambdaLogger logger = LambdaLoggerFactory.getLogger(getClass());

    @Autowired
    CompanyService companyService;

    /**
     * 企业信息录入
     * @param request  http请求
     * @param response http响应
     * @return
     */
    @RequestMapping(value = "/entryInfo", method = RequestMethod.POST)
//    public ApiResult entryInfoWithAuth(CompanyInfoReqPojo companyInfoReqPojo,
    public ApiResult entryInfo(CompanyInfoReqPojo companyInfoReqPojo,
                               OAuthUserInfo oui,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        ApiResult result = new ApiResult();

        try {
            //存储企业注册的相关信息
            result = companyService.entryCompanyInfo(companyInfoReqPojo, oui);
        } catch(Exception e) {
            logger.error("Entry Company Information error :",e);
            result.setFlag(BaseService.FLAG_F);
            result.setReturnCode(BaseService.BR_OTHER_ERROR);
            result.setReturnInfo("录入企业信息未成功。");
        }
        return result;
    }

    /**
     * 企业信息展示
     * @param request  http请求
     * @param response http响应
     * @return
     */
    @RequestMapping(value = "/queryInfo", method = RequestMethod.POST)
//    public ApiResult queryInfoWithAuth(OAuthUserInfo oui,
    public ApiResult queryInfo(OAuthUserInfo oui, CompanyInfoReqPojo companyInfoReqPojo,
                                       HttpServletRequest request,
                                       HttpServletResponse response) {
        ApiResult result = new ApiResult();

        try {
            //测试阶段！！！！！需要注销
//            String userId = oui.info.attrs.get("UserloginId");
            String userId = "userID_32ysh45387";
            CompanyInfoRspPojo companyInfoRspPojo = new CompanyInfoRspPojo();
            //存储企业注册的相关信息
            result = companyService.queryCompanyInfo(userId, companyInfoReqPojo.getUrl());

        } catch(Exception e) {
            logger.error("Query Company Information error :",e);
            result.setFlag(BaseService.FLAG_F);
            result.setReturnCode(BaseService.BR_OTHER_ERROR);
            result.setReturnInfo("查询出现错误。");
        }
        return result;
    }
    
    
}
