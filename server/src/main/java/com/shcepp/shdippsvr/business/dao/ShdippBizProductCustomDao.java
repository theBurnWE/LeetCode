package com.shcepp.shdippsvr.business.dao;

import com.shcepp.shdippsvr.business.entity.ProductEntity;

import java.util.List;

/**
 * @author BrunE
 * @date 2019-07-18 20:49
 **/

public interface ShdippBizProductCustomDao {
    int countProductByCategory(String dataDomain, String lan, String category);

    List<String> queryNewlyProductIdByCategory(String dataDomain, String lan, int top, String category);

    List<ProductEntity> queryProductByIdList(List<String> prdIdList);

    List<ProductEntity> queryRankByLikes(String dataDomain, String lan, int top);

    List<ProductEntity> queryRankByFavs(String dataDomain, String lan, int top);

    List<ProductEntity> queryRankByRecommendation(String dataDomain, String lan, int top);

    List<ProductEntity> queryRankByNewly(String dataDomain, String lan, int top);
}
