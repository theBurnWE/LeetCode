package com.shcepp.shdippsvr.business.service.impl;

import com.shcepp.shdippsvr.business.dao.ShdippBizUserFavoriteDao;
import com.shcepp.shdippsvr.business.entity.ShdippBizUserFavoriteEntity;
import com.shcepp.shdippsvr.business.service.InteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedSet;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zkmao
 * @description
 * @date 2019/9/3 19:48
 */
@Service
public class InterfactionServiceImpl implements InteractionService {
    
    @Autowired
    InteractionService interactionService;
    
    @Autowired
    ShdippBizUserFavoriteDao shdippBizUserFavoriteDao;
    
    @Override
    public List<ShdippBizUserFavoriteEntity> queryUserFavorites(String userId) {
        return shdippBizUserFavoriteDao.findByUserId(userId);
    }
    
    @Override
    public Set<String> queryUserFavPrdId(String userId) {
        List <ShdippBizUserFavoriteEntity> list = interactionService.queryUserFavorites(userId);
        Set<String> ids = new HashSet<>();
        //遍历收藏列表，若收藏项目类型是产品，则将id加入set
        for(ShdippBizUserFavoriteEntity entity : list){
            if ("".equals(entity.getItemType())){
                ids.add(entity.getFavoriteItemId());
            }
        }
        return ids;
    }
}
