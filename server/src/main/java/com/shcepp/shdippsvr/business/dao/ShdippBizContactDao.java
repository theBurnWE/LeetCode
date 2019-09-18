package com.shcepp.shdippsvr.business.dao;

import com.shcepp.shdippsvr.business.entity.ShdippBizContactEntity;
import com.shcepp.shdippsvr.sys.dao.EpRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author BrunE
 * @date 2019-07-18 20:49
 **/
@Repository
@Transactional
public interface ShdippBizContactDao extends EpRepository<ShdippBizContactEntity, Long> {

    ShdippBizContactEntity findTopByUserIdAndDelFlagOrderByDispOrderAsc(String userId, String delFlag);
}
