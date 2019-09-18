package com.shcepp.shdippsvr.business.service.impl;

import com.shcepp.shdippsvr.business.bean.pojo.recommended.RecommendedResourcesResPojo;
import com.shcepp.shdippsvr.business.bean.pojo.recommended.RecommendedResourcesReqPojo;
import com.shcepp.shdippsvr.business.bean.pojo.recommended.RecommendedResourcesRspHeaderPojo;
import com.shcepp.shdippsvr.business.enums.ModuleType;
import com.shcepp.shdippsvr.business.service.BaseService;
import com.shcepp.shdippsvr.business.service.RecommendedResourcesService;
import com.shcepp.shdippsvr.sys.util.ApiResult;
import com.shcepp.shdippsvr.sys.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static com.shcepp.shdippsvr.business.enums.ModuleType.*;

/**
 * 推荐资源的加载sevice
 *
 * @author BrunE
 * @date 2019-07-18 14:10
 **/

@Service
@Transactional
public class RecommendedResourcesServiceImpl implements RecommendedResourcesService {

    private static final Logger logger = LoggerFactory.getLogger(RecommendedResourcesServiceImpl.class);
    @Autowired
    private PlatServiceImpl platServiceImpl;
    @Autowired
    private CompanyServiceImpl companyServiceImpl;
    @Autowired
    private ProductServiceImpl productServiceImpl;
    @Autowired
    private HomePageRecommendedServiceImpl homePageRecommendedServiceImpl;

    @Override
    public ApiResult queryHomePageRecommendedResources(RecommendedResourcesReqPojo pojo) {

        ApiResult result = new ApiResult();

        RecommendedResourcesRspHeaderPojo rspPojo = new RecommendedResourcesRspHeaderPojo();

        String moduleTypeArray = pojo.getModuleTypeArray();

        List<String> moduleTypeList;

        if (moduleTypeArray != null) {

            moduleTypeList = Arrays.asList(moduleTypeArray.split(","));
            //线程阻塞式循环查询各个业务类型的推荐位

            for (String moduleType: moduleTypeList) {
                pojo.setModuleType(moduleType);
                dealQuery(pojo, rspPojo);
            }
//            moduleTypeList.forEach(moduleType -> {
//
//            });
            result.setData(rspPojo);
            result.setReturnCode(BR_SUCCESS);
            result.setFlag(BaseService.FLAG_T);
            result.setReturnInfo(BR_SUCCESS_MESSAGE);

        } else {
            //查询类型为空返回异常信息
            result.setReturnInfo(WITCHOUT_MODETYPE_MESSAGE);
            result.setFlag(BaseService.FLAG_F);
            result.setReturnCode(WITCHOUT_MODETYPE);

        }

        return result;
    }

    @Override
    public RecommendedResourcesRspHeaderPojo dealQuery(RecommendedResourcesReqPojo pojo, RecommendedResourcesRspHeaderPojo result) {
        List<RecommendedResourcesResPojo> resList;
        switch (ModuleType.getEnumsByCode(pojo.getModuleType())) {
            case MT_00:
                logger.info("开始类型为{}的首页推荐数据查询", MT_00.getValue());
                resList = homePageRecommendedServiceImpl.queryHomePageRecommendedResource(pojo);
                logger.info("查询结果为：{}", JsonUtil.beanToJson(resList));
                result.setHomePageRecommendedList(resList);
                break;
            case MT_01:
                logger.info("开始类型为{}的首页推荐数据查询", MT_01.getValue());
                resList = platServiceImpl.queryHomePagePlatRecommendedResources(pojo);
                logger.info("查询结果为：{}", JsonUtil.beanToJson(resList));
                result.setPlatRecommendedList(resList);
                break;
            case MT_02:
                logger.info("开始类型为{}的首页推荐数据查询", MT_02.getValue());
                resList = companyServiceImpl.queryHomePageCompanyRecommendedResources(pojo);
                result.setCompanyRecommendedList(resList);
                logger.info("查询结果为：{}", JsonUtil.beanToJson(resList));
                break;
            case MT_03:   // 传入参数为ALL时代码稍微有点重复， 待优化
                logger.info("开始类型为{}的首页推荐数据查询", MT_03.getValue());
                resList = productServiceImpl.queryHomePageProductRecommendedResources(pojo);
                logger.info("查询结果为：{}", JsonUtil.beanToJson(resList));
                result.setProductRecommendedList(resList);
                break;

            default:
                logger.error("传入的模块类型异常，无法接续");
                break;
        }

        return result;
    }
}
