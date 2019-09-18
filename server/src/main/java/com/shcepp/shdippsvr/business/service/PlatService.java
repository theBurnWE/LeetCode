package com.shcepp.shdippsvr.business.service;

import com.shcepp.shdippsvr.business.bean.pojo.recommended.RecommendedResourcesReqPojo;
import com.shcepp.shdippsvr.business.bean.pojo.recommended.RecommendedResourcesResPojo;
import com.shcepp.shdippsvr.business.entity.ShdippBizEntInfoDetailEntity;
import com.shcepp.shdippsvr.business.entity.ShdippBizPlatformDetailEntity;
import com.shcepp.shdippsvr.business.entity.ShdippBizRecommendationEntity;
import com.shcepp.shdippsvr.business.entity.view.VPlatformInfoDetailEntity;

import java.util.List;

/**
 * 平台业务处理的service
 *
 * @author BrunE
 * @date 2019-07-18 14:06
 **/
public interface PlatService extends BaseService {

    /**
     * 执行首页资源加载
     *
     * @param pojo 页面传入的查询参数
     * @return 查询结果
     */
    List<RecommendedResourcesResPojo> queryHomePagePlatRecommendedResources(RecommendedResourcesReqPojo pojo);

    /**
     * 根据ID加载首页站点资源
     *
     * @param id      业务明细ID
     * @param status  状态
     * @param baseURL 基础url
     * @return 资源结果
     */
    RecommendedResourcesResPojo getHomePagePlatRecommendedResources(String id, String status, long sort, String baseURL);

    /**
     * 根据推荐位顺序和自然顺序调整排序
     *
     * @param platformDetailEntities 自然排序数据
     * @param recommendationList     推荐位排序数据
     * @return 展示结果
     */
    List<RecommendedResourcesResPojo> sortAndFormatPlatRecommend(String status,
                                                                 String BaseURL,
                                                                 List<ShdippBizRecommendationEntity> recommendationList,
                                                                 List<ShdippBizPlatformDetailEntity> platformDetailEntities);

    /**
     * 格式化首页bean
     *
     * @param entity     视图实体
     * @param baseURL    baseURL
     * @param entityList 关联实体数据
     * @return 结果
     */
    RecommendedResourcesResPojo formatHomPageBean(VPlatformInfoDetailEntity entity,
                                                  String baseURL,
                                                  long sort,
                                                  List<ShdippBizEntInfoDetailEntity> entityList);

}
