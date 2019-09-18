package com.shcepp.shdippsvr.business.service;

import com.shcepp.shdippsvr.business.bean.pojo.recommended.RecommendedResourcesResPojo;
import com.shcepp.shdippsvr.business.bean.pojo.recommended.RecommendedResourcesReqPojo;

import java.util.List;

/**
 * 首页的首页栏推荐资源加载Service
 *
 * @author BrunE
 * @date 2019-07-18 14:06
 **/
public interface HomePageRecommendedService extends BaseService {

    /**
     * 没有传递模块业务类型
     */
    static final String WITCHOUT_MODETYPE = "910";

    /**
     * 没有传递模块业务类型
     */
    static final String WITCHOUT_MODETYPE_MESSAGE = "未传递模块业务类型";



    List<RecommendedResourcesResPojo> queryHomePageRecommendedResource(RecommendedResourcesReqPojo pojo);

    List<RecommendedResourcesResPojo> doDetailQuery(RecommendedResourcesReqPojo id, List<RecommendedResourcesResPojo> homePageResList, long sorId);

}
