package com.shcepp.shdippsvr.business.service;

import com.shcepp.shdippsvr.business.bean.pojo.PlatformEnterpriseCategoryPojo;
import com.shcepp.shdippsvr.business.bean.pojo.PlatformEnterpriseDetailReqPojo;
import com.shcepp.shdippsvr.business.bean.pojo.PlatformEnterpriseDetailResPojo;

import java.util.List;

/**
 * Created by shcepp on 2019/9/2.
 */
public interface PlatformService extends BaseService{
    /**查询平台详细数据*/
    List<PlatformEnterpriseDetailResPojo> queryPlatformDetail(PlatformEnterpriseDetailReqPojo platformEnterpriseDetailReqPojo);

    /**查询平台数据*/
    PlatformEnterpriseCategoryPojo queryPlatform(PlatformEnterpriseDetailReqPojo platformEnterpriseDetailReqPojo);
}
