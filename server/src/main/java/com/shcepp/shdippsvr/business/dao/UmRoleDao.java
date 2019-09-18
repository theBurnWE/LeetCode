package com.shcepp.shdippsvr.business.dao;

import com.shcepp.shdippsvr.business.entity.UmRoleEntity;
import com.shcepp.shdippsvr.sys.dao.EpRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by EasonXu on 2018/4/10.
 */
@Repository
@Transactional
public interface UmRoleDao extends EpRepository<UmRoleEntity, Long> {
    UmRoleEntity findFirstByRoleCode(String roleCode);
}


