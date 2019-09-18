package com.shcepp.shdippsvr.business.service;

import com.shcepp.shdippsvr.business.entity.ShdippBizUserFavoriteEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 互动操作的service
 *
 * @author zkmao
 * @date 2019-09-03 19:45
 **/
@Service
public interface InteractionService extends BaseService {
    
    /**
     * 根据用户id获取收藏产品列表
     * @param userId 用户id
     * @return
     */
    List<ShdippBizUserFavoriteEntity> queryUserFavorites(String userId);
    
    /**
     * 根据用户id获取收藏产品列表Id
     * @param userId 用户id
     * @return
     */
    Set<String> queryUserFavPrdId(String userId);
    
}
