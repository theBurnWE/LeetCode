package com.shcepp.shdippsvr.business.dao;

import com.shcepp.shdippsvr.business.entity.UmBizAuditEntity;
import com.shcepp.shdippsvr.sys.dao.EpRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by EasonXu on 2018/4/10.
 */
@Repository
@Transactional
public interface UmBizAuditDao extends EpRepository<UmBizAuditEntity, Long> {

}


