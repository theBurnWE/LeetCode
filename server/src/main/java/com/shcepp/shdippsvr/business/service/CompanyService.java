package com.shcepp.shdippsvr.business.service;

import com.shcepp.shdippsvr.business.bean.pojo.recommended.RecommendedResourcesReqPojo;
import com.shcepp.shdippsvr.business.bean.pojo.recommended.RecommendedResourcesResPojo;
import com.shcepp.shdippsvr.business.bean.pojo.resources.CompanyInfoReqPojo;
import com.shcepp.shdippsvr.business.entity.ShdippBizEntInfoDetailEntity;
import com.shcepp.shdippsvr.business.entity.ShdippBizPlatformDetailEntity;
import com.shcepp.shdippsvr.business.entity.ShdippBizRecommendationEntity;
import com.shcepp.shdippsvr.business.entity.view.VEntRecInfoDetailEntity;
import com.shcepp.shdippsvr.oauth.dto.OAuthUserInfo;
import com.shcepp.shdippsvr.sys.util.ApiResult;

import java.util.List;

/**
 * 企业业务处理的service
 *
 * @author BrunE
 * @date 2019-07-18 14:06
 **/
public interface CompanyService extends BaseService {

    /**
     * 企业推荐资源加载
     * 默认查询
     *
     * @param pojo 页面传入的查询参数
     * @return 查询结果
     */
    List<RecommendedResourcesResPojo> queryHomePageCompanyRecommendedResources(RecommendedResourcesReqPojo pojo);

    /**
     * 根据ID进行企业推荐资源加载
     *
     * @param id      数据ID
     * @param status  状态
     * @param baseUrl 基础url
     * @return 组装结果
     */
    RecommendedResourcesResPojo queryHomePageCompanyRecommendedResources(String id, String status, long sort, String baseUrl);

    /**
     * 根据推荐位顺序和自然顺序调整排序
     *
     * @param companyDetailEntities 自然排序数据
     * @param recommendationList    推荐位排序数据
     * @return 展示结果
     */
    List<RecommendedResourcesResPojo> sortAndFormatPlatRecommend(String status,
                                                                 String BaseURL,
                                                                 List<ShdippBizRecommendationEntity> recommendationList,
                                                                 List<ShdippBizEntInfoDetailEntity> companyDetailEntities);

    /**
     * 组装查询结果参数
     *
     * @param entity     实体类
     * @param baseURL    基础url
     * @param entityList 附属数据
     * @return 组装结果
     */
    RecommendedResourcesResPojo formatHomPageBean(VEntRecInfoDetailEntity entity,
                                                  String baseURL,
                                                  long sort,
                                                  List<ShdippBizPlatformDetailEntity> entityList);
    /**
     * 企业信息录入
     * @param companyInfoReqPojo
     * @return 返回结果
     *
     */
    ApiResult entryCompanyInfo(CompanyInfoReqPojo companyInfoReqPojo, OAuthUserInfo oui);

    /**
     * 企业信息查询
     * @param userId
     * @return 返回结果
     *
     */
    ApiResult queryCompanyInfo(String userId, String url);
}
