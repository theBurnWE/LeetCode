package com.shcepp.shdippsvr.business.service;

import com.shcepp.shdippsvr.business.bean.pojo.recommended.RecommendedResourcesReqPojo;
import com.shcepp.shdippsvr.business.bean.pojo.recommended.RecommendedResourcesRspHeaderPojo;
import com.shcepp.shdippsvr.sys.util.ApiResult;

/**
 * 推荐资源加载Service
 *
 * @author BrunE
 * @date 2019-07-18 14:06
 **/
public interface RecommendedResourcesService extends BaseService {
    /**
     * 没有传递模块业务类型
     */
    static final String WITCHOUT_MODETYPE = "910";

    /**
     * 没有传递模块业务类型
     */
    static final String WITCHOUT_MODETYPE_MESSAGE = "未传递模块业务类型";

    /**
     * 执行首页资源加载
     *
     * @param pojo 页面传入的查询参数
     * @return 查询结果
     */
    ApiResult queryHomePageRecommendedResources(RecommendedResourcesReqPojo pojo);

    /**
     * 根据参数类型的不同执行对应的查询
     *
     * @param pojo 页面传入的查询参数
     * @return 查询结果
     */
    RecommendedResourcesRspHeaderPojo dealQuery(RecommendedResourcesReqPojo pojo, RecommendedResourcesRspHeaderPojo result);
}
