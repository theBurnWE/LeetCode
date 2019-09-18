package com.shcepp.shdippsvr.business.service;

import com.shcepp.shdippsvr.business.bean.pojo.SysBizAuditPojo;

/**
 * 审计的service用于
 *
 * @author BrunE
 * @date 2018-04-03 15:51
 **/
public interface SysBizAuditService extends BaseService {

    //在保存成功的情况下返回true
    public boolean SaveAudit(SysBizAuditPojo objItem);
}
