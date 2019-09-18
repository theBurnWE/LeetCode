package com.shcepp.shdippsvr.business.service;

import com.shcepp.shdippsvr.business.bean.pojo.ProductCategoryPojo;
import com.shcepp.shdippsvr.business.bean.pojo.ProductConditionPojo;
import com.shcepp.shdippsvr.business.bean.pojo.ProductRanksPojo;
import com.shcepp.shdippsvr.business.bean.pojo.recommended.RecommendedResourcesReqPojo;
import com.shcepp.shdippsvr.business.bean.pojo.recommended.RecommendedResourcesResPojo;
import com.shcepp.shdippsvr.business.bean.pojo.resources.ProductInfoReqPojo;
import com.shcepp.shdippsvr.business.entity.ShdippBizProductDetailEntity;
import com.shcepp.shdippsvr.business.entity.ShdippBizRecommendationEntity;
import com.shcepp.shdippsvr.business.entity.view.VProductInfoDetailEntity;
import com.shcepp.shdippsvr.sys.util.ApiResult;

import java.util.List;

/**
 * 产品业务处理的service
 *
 * @author BrunE
 * @date 2019-07-18 14:06
 **/
public interface ProductService extends BaseService {

    /**
     * 执行产品首页资源加载
     *
     * @param pojo 页面传入的查询参数
     * @return 查询结果
     */
    List<RecommendedResourcesResPojo> queryHomePageProductRecommendedResources(RecommendedResourcesReqPojo pojo);

    /**
     * 根据ID进行产品首页资源加载
     *
     * @param id      业务明细ID
     * @param status  状态
     * @param BaseURL 基础url
     * @return 资源结果
     */
    RecommendedResourcesResPojo getHomePageProductRecommendedResources(String id, String status, long sort, String BaseURL);

    /**
     * 根据推荐位顺序和自然顺序调整排序
     *
     * @param productDetailEntities 自然排序数据
     * @param recommendationList    推荐位排序数据
     * @return 展示结果
     */
    List<RecommendedResourcesResPojo> sortAndFormatPlatRecommend(String status,
                                                                 String BaseURL,
                                                                 List<ShdippBizRecommendationEntity> recommendationList,
                                                                 List<ShdippBizProductDetailEntity> productDetailEntities);

    /**
     * 格式化首页bean
     *
     * @param entity     视图实体
     * @param baseURL    baseURL
     * @param entityList 关联实体数据
     * @return 结果
     */
    RecommendedResourcesResPojo formatHomPageBean(VProductInfoDetailEntity entity,
                                                  String baseURL,
                                                  long sort,
                                                  List<ShdippBizProductDetailEntity> entityList);


    /**

     * 根据条件返回单个类别的产品列表
     *
     * @param condition 查询条件
     * @return
     */
    ProductCategoryPojo retrieveProductByCategory(ProductConditionPojo condition);

    /**
     * 获得产品排名
     *
     * @return
     */
    ProductRanksPojo retrieveProductRanks(ProductConditionPojo condition);


    
    /**
     * 将排名信息保存到缓存中
     * @return
     */
    ProductRanksPojo storeProductRanksToCache(ProductConditionPojo condition);


    /**
     * 点赞产品
     * @param prdId 产品id
     */
    void productLike(String prdId);

    /**
     * 收藏/取消收藏产品
     * @param userId 用户id
     * @param prdId 产品id
     * @param favFlag 收藏标志 0: 取消收藏 1: 收藏
     */
    void productFav(String userId, String prdId, String favFlag);
    /**
     * 查询产品详情
     */
    ApiResult queryProductInfo(ProductInfoReqPojo productInfoReqPojo,String userId);
}
