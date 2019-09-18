package com.shcepp.shdippsvr.business.service;

public interface IpLimitService {
    /**
     * 检查ip访问是否超过限制
     *
     * @param ip
     * @return
     */
    boolean checkIpCount(String ip);
    
    /**
     * 保存登录成功的账号
     *
     * @param ip
     * @param userName
     */
    void saveIpLog(String ip, String userName);
}
