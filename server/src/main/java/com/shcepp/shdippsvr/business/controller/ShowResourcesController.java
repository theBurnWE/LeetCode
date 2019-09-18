package com.shcepp.shdippsvr.business.controller;

import com.shcepp.shdippsvr.business.bean.pojo.recommended.RecommendedResourcesReqPojo;
import com.shcepp.shdippsvr.business.service.BaseService;
import com.shcepp.shdippsvr.business.service.RecommendedResourcesService;
import com.shcepp.shdippsvr.sys.controller.BaseController;
import com.shcepp.shdippsvr.sys.util.ApiResult;
import com.shcepp.shdippsvr.sys.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;

/**
 * @Description: 数字贸易项目首页请求资源
 * @author: Burn~E
 * @date: 2019/08/19 11:06
 * @version: V1.0
 */
@RestController
@RequestMapping("recommended/")
public class ShowResourcesController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ShowResourcesController.class);
    @Autowired
    private RecommendedResourcesService recommendedResourcesService;

    /**
     * 数字贸易项目首页请求资源
     *
     * @param request  http请求
     * @param response http响应
     * @return 响应报文
     */
    @PostMapping(value = "homepage/recommendedResources")
    public ApiResult homePageLoad(RecommendedResourcesReqPojo recommendedResourcesPojo,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        recommendedResourcesPojo.setRecommendationStatus("1");
        logger.info("get hompage  query recommended resources request , reqMessage is {} ",
                    JsonUtil.beanToJson(recommendedResourcesPojo));

        //认证的部分已经在filter中完成
        //在此处只执行查询任务
        ApiResult result;

        try {
            result = recommendedResourcesService.queryHomePageRecommendedResources(recommendedResourcesPojo);
            logger.info("finish hompage  query recommended resources, response is {} ",
                        JsonUtil.beanToJson(result));
            return result;

        } catch (ValidationException ver) {
            logger.error("validate req fial error message is ", ver);
            result = new ApiResult();

            //在参数校验失败的时候
            result.setReturnCode(ver.getMessage());
            result.setReturnInfo(BaseService.BR_OTHER_ERROR);
            result.setFlag(BaseService.FLAG_F);
        } catch (Exception err) {
            logger.error("unexpect  error message is ", err);
            result = new ApiResult();

            result.setReturnCode(BaseService.BR_OTHER_ERROR);
            result.setReturnInfo(BaseService.BR_OTHER_ERROR_MESSAGE);
            result.setFlag(BaseService.FLAG_F);
        }
        return result;
    }

}
