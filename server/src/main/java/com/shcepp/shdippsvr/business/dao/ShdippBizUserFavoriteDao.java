package com.shcepp.shdippsvr.business.dao;

import com.shcepp.shdippsvr.business.entity.ShdippBizUserFavoriteEntity;
import com.shcepp.shdippsvr.sys.dao.EpRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author BrunE
 * @date 2019-07-18 20:49
 **/
@Repository
@Transactional
public interface ShdippBizUserFavoriteDao extends EpRepository<ShdippBizUserFavoriteEntity, Long> {
    List<ShdippBizUserFavoriteEntity> findByUserId(String userId);


    ShdippBizUserFavoriteEntity findByUserIdAndAndItemTypeAndAndFavoriteItemId(String userId, long itemType, String itemId);
}
