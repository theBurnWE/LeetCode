package com.shcepp.shdippsvr.business.controller;

import com.shcepp.shdippsvr.business.bean.pojo.PlatformEnterpriseCategoryPojo;
import com.shcepp.shdippsvr.business.bean.pojo.PlatformEnterpriseDetailReqPojo;
import com.shcepp.shdippsvr.business.bean.pojo.PlatformEnterpriseDetailResPojo;
import com.shcepp.shdippsvr.business.service.BaseService;
import com.shcepp.shdippsvr.business.service.PlatformService;
import com.shcepp.shdippsvr.sys.controller.BaseController;
import com.shcepp.shdippsvr.sys.util.ApiResult;
import com.shcepp.shdippsvr.sys.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by gxd on 2019/9/2.
 * 平台信息展示
 */
@RestController
@RequestMapping("platform/")
public class ShowPlatformController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(ShowPlatformController.class);
    @Autowired
    private PlatformService platformService;

    @PostMapping(value = "/showPlatformDetail")
    public ApiResult queryPlatformDetail(PlatformEnterpriseDetailReqPojo platformEnterpriseDetailReqPojo) {
        logger.info("get platformDetailPage query platformDetail resources request , reqMessage is {} ",
                JsonUtil.beanToJson(platformEnterpriseDetailReqPojo));
        ApiResult apiResult;
        try{

            List<PlatformEnterpriseDetailResPojo> platformEnterpriseDetailResPojoList = platformService.queryPlatformDetail(platformEnterpriseDetailReqPojo);
            apiResult=new ApiResult(BaseService.FLAG_T,BaseService.BR_SUCCESS,BaseService.BR_SUCCESS_MESSAGE, platformEnterpriseDetailResPojoList);
        }catch (Exception e){
            apiResult=new ApiResult(BaseService.FLAG_F,BaseService.BR_OTHER_ERROR,e.getMessage(),null);
        }

        return apiResult;
    }

    @PostMapping(value = "/showPlatform")
    public ApiResult queryPlatform(PlatformEnterpriseDetailReqPojo platformEnterpriseDetailReqPojo) {
        logger.info("get platformDetailPage query platform resources request , reqMessage is {} ",
                JsonUtil.beanToJson(platformEnterpriseDetailReqPojo));
        ApiResult apiResult;
        try{
            PlatformEnterpriseCategoryPojo platformEnterpriseDetailResPojoList = platformService.queryPlatform(platformEnterpriseDetailReqPojo);
            apiResult=new ApiResult(BaseService.FLAG_T,BaseService.BR_SUCCESS,BaseService.BR_SUCCESS_MESSAGE, platformEnterpriseDetailResPojoList);
        }catch (Exception e){
            apiResult=new ApiResult(BaseService.FLAG_F,BaseService.BR_OTHER_ERROR,e.getMessage(),null);
        }

        return apiResult;
    }
}
