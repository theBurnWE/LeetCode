package com.shcepp.shdippsvr.business.service.impl;

import com.shcepp.shdippsvr.business.bean.pojo.recommended.RecommendedResourcesReqPojo;
import com.shcepp.shdippsvr.business.bean.pojo.recommended.RecommendedResourcesResPojo;
import com.shcepp.shdippsvr.business.config.HomePageConfig;
import com.shcepp.shdippsvr.business.dao.ShdippBizRecommendationDao;
import com.shcepp.shdippsvr.business.entity.ShdippBizRecommendationEntity;
import com.shcepp.shdippsvr.business.enums.ModuleType;
import com.shcepp.shdippsvr.business.service.FileService;
import com.shcepp.shdippsvr.business.service.HomePageRecommendedService;
import com.shcepp.shdippsvr.sys.util.JsonUtil;
import com.shcepp.shdippsvr.sys.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
public class HomePageRecommendedServiceImpl implements HomePageRecommendedService {

    private static final Logger logger = LoggerFactory.getLogger(HomePageRecommendedServiceImpl.class);
    @Autowired
    private PlatServiceImpl platServiceImpl;
    @Autowired
    private CompanyServiceImpl companyServiceImpl;
    @Autowired
    private ProductServiceImpl productServiceImpl;
    @Autowired
    private FileService fileService;

    @Autowired
    private ShdippBizRecommendationDao shdippBizRecommendationDao;
    @Autowired
    private RecommendedResourcesServiceImpl recommendedResourcesServiceImpl;

    @Override
    public List<RecommendedResourcesResPojo> queryHomePageRecommendedResource(RecommendedResourcesReqPojo pojo) {
        ShdippBizRecommendationEntity entity;
        List<ShdippBizRecommendationEntity> homePageList = new ArrayList<>();
        List<RecommendedResourcesResPojo> homePageResList = new ArrayList<>();

        homePageList = shdippBizRecommendationDao.queryByRecommTypeAndLanAndDataDomainAndStatus(pojo.getModuleType(),
                                                                                                pojo.getLanCode(),
                                                                                                pojo.getDominCode(),
                                                                                                pojo.getRecommendationStatus(),
                                                                                                HomePageConfig.homePageHomePageSize);

        if (homePageList.size() <= 0) {
            logger.info("在对首页进行传入参数为{} 的查询时，未查询到数据", JsonUtil.beanToJson(pojo));
            return null;
        }

        for (int i = 0; i < homePageList.size(); i++) {
            entity = homePageList.get(i);
            //在推荐类别不为空且不等于首页类型的时候进行查询操作
            if (StringUtils.isNotEmptyWithNUllCheckStr(entity.getItemCategory()) && !MT_00.getCode().equals(entity.getItemCategory())) {
                pojo.setModuleType(entity.getItemCategory());
                pojo.setDetailId(entity.getId());
                homePageResList = doDetailQuery(pojo, homePageResList, i);
            } else {

                homePageResList.add(formatHomePageResoseBean(pojo.getBaseUrl(), entity, ((long) i)));
            }
        }
        return homePageResList;
    }

    @Override
    public List<RecommendedResourcesResPojo> doDetailQuery(RecommendedResourcesReqPojo pojo,
                                                           List<RecommendedResourcesResPojo> homePageResList,
                                                           long sorId) {
        RecommendedResourcesResPojo resourcePojo;
        switch (ModuleType.getEnumsByCode(pojo.getModuleType())) {

            case MT_01:
                logger.info("开始进行详细数据为{}的首页推荐数据查询,类型为{}", JsonUtil.beanToJson(pojo), MT_01.getValue());
                resourcePojo = platServiceImpl.getHomePagePlatRecommendedResources(pojo.getDetailId(),
                                                                                   pojo.getRecommendationStatus(),
                                                                                   sorId,
                                                                                   pojo.getBaseUrl());
                resourcePojo.setSort(sorId);
                resourcePojo.setBusinessType(MT_01.getCode());
                homePageResList.add(resourcePojo);
                break;
            case MT_02:
                logger.info("开始进行详细数据为{}的首页推荐数据查询,类型为{}", JsonUtil.beanToJson(pojo), MT_02.getValue());
                resourcePojo = companyServiceImpl.queryHomePageCompanyRecommendedResources(pojo.getDetailId(),
                                                                                           pojo.getRecommendationStatus(),
                                                                                           sorId,
                                                                                           pojo.getBaseUrl());
                resourcePojo.setSort(sorId);
                resourcePojo.setBusinessType(MT_02.getCode());
                homePageResList.add(resourcePojo);
                break;
            case MT_03:   // 传入参数为ALL时代码稍微有点重复， 待优化
                logger.info("开始进行详细数据为{}的首页推荐数据查询,类型为{}", JsonUtil.beanToJson(pojo), MT_03.getValue());
                resourcePojo = productServiceImpl.getHomePageProductRecommendedResources(pojo.getDetailId(),
                                                                                         pojo.getRecommendationStatus(),
                                                                                         sorId,
                                                                                         pojo.getBaseUrl());
                resourcePojo.setSort(sorId);
                resourcePojo.setBusinessType(MT_03.getCode());
                homePageResList.add(resourcePojo);
                break;
            default:
                logger.error("传入的模块类型异常，无法接续");
                break;
        }

        return homePageResList;
    }

    /**
     * 根据查询结果封装向前台返回的bean
     *
     * @param entity 查询结果
     * @return 封装好的返回值
     */
    private RecommendedResourcesResPojo formatHomePageResoseBean(String baseURL, ShdippBizRecommendationEntity entity, long sort) {
        RecommendedResourcesResPojo rsp = new RecommendedResourcesResPojo();
        rsp.setBusinessType(MT_00.getCode());
        //如果是首页的推荐位的话直接返回URL
        rsp.setTransversePicUrlPicUrl(entity.getRecommContent());
//        rsp.setTransversePicUrlPicUrl(fileService.getFileUrl(baseURL, entity.getRecommContent(), null));
        rsp.setJumpLink(entity.getRecommNav());
        rsp.setSort(sort);
        return rsp;

    }

}
