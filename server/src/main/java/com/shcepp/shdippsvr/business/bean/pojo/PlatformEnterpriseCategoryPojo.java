package com.shcepp.shdippsvr.business.bean.pojo;

import com.shcepp.shdippsvr.business.bean.BaseBean;

import java.util.List;

/**
 * Created by shcepp on 2019/9/11.
 */
public class PlatformEnterpriseCategoryPojo extends BaseBean {
    private static final long serialVersionUID = -3242215357142361699L;
    public String category;
    public List<PlatformEnterpriseDetailResPojo> list;
}
