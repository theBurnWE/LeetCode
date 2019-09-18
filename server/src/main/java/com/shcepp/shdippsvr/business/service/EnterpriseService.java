package com.shcepp.shdippsvr.business.service;

import com.shcepp.shdippsvr.business.bean.pojo.PlatformEnterpriseCategoryPojo;
import com.shcepp.shdippsvr.business.bean.pojo.PlatformEnterpriseDetailReqPojo;
import com.shcepp.shdippsvr.business.bean.pojo.PlatformEnterpriseDetailResPojo;

import java.util.List;

/**
 * Created by gxd on 2019/9/3.
 */
public interface EnterpriseService extends BaseService{
    /**查询企业详细数据*/
    List<PlatformEnterpriseDetailResPojo> queryEnterpriseDetail(PlatformEnterpriseDetailReqPojo platformEnterpriseDetailReqPojo);

    /**查询企业数据*/
    PlatformEnterpriseCategoryPojo queryEnterprise(PlatformEnterpriseDetailReqPojo platformEnterpriseDetailReqPojo);
}
