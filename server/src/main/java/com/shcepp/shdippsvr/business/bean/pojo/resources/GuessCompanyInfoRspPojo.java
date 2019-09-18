package com.shcepp.shdippsvr.business.bean.pojo.resources;

import com.shcepp.shdippsvr.business.bean.BaseResponseBean;

/**
 * 企业信息维护消息
 *
 * @author IvanXu
 * @date 2019-09-03
 **/
public class GuessCompanyInfoRspPojo extends BaseResponseBean {

    private static final long serialVersionUID = 4616001288493991842L;
    private String companyName;
    private String compangProfile;
    private String companyLogoUrl;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompangProfile() {
        return compangProfile;
    }

    public void setCompangProfile(String compangProfile) {
        this.compangProfile = compangProfile;
    }

    public String getCompanyLogoUrl() {
        return companyLogoUrl;
    }

    public void setCompanyLogoUrl(String companyLogoUrl) {
        this.companyLogoUrl = companyLogoUrl;
    }
}
