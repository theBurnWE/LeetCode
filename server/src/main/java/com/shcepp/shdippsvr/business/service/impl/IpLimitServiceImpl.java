package com.shcepp.shdippsvr.business.service.impl;


import com.shcepp.shdippsvr.business.dao.BaseIpInLogDao;
import com.shcepp.shdippsvr.business.entity.BaseIpInLogEntity;
import com.shcepp.shdippsvr.business.service.IpLimitService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
public class IpLimitServiceImpl implements IpLimitService {
    @Autowired
    private BaseIpInLogDao baseIpInLogDao;
    
    final int USER_LIMIT_MINS = 60;
    final int USER_LIMIT_TIMES = 30;
    
    
    final int USER_LIMIT_ENABLE = 1;
    
    
    @Override
    public boolean checkIpCount(String ip) {
        if(1 != USER_LIMIT_ENABLE) {
            return true;
        }
        if(StringUtils.isBlank(ip)) {
//            ip = "null";
            return true;
        }
        ip = ip.trim();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -USER_LIMIT_MINS);
        int count = baseIpInLogDao.countBaseIpInLogEntitiesByCreateTimeGreaterThanEqualAndIp(calendar.getTime(), ip);
        if(count >= USER_LIMIT_TIMES) {
            return false;
        }
        return true;
    }
    
    @Override
    public void saveIpLog(String ip, String userName) {
        
        if(StringUtils.isBlank(ip)) {
            ip = "";
//            return;
        }
        ip = ip.trim();
        Date date = new Date();
        BaseIpInLogEntity ipInLog = new BaseIpInLogEntity();
        ipInLog.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
        ipInLog.setCreateTime(date);
        ipInLog.setIp(ip);
        if(userName.length() > 100) {
            userName.substring(0, 100);
        }
        ipInLog.setSpt(userName);
        baseIpInLogDao.save(ipInLog);
    }
    
}
