package com.shcepp.shdippsvr.business.dao;

import com.shcepp.shdippsvr.business.entity.BaseIpInLogEntity;
import com.shcepp.shdippsvr.sys.dao.EpRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BaseIpInLogDao extends EpRepository<BaseIpInLogEntity, String> {
    int countBaseIpInLogEntitiesByCreateTimeGreaterThanEqualAndIp(Date createTime, String ip);
    
    List<BaseIpInLogEntity> getFirst2BySptOrderByCreateTimeDesc(String spt);
}
