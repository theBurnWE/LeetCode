package com.shcepp.shdippsvr.business.bean.pojo.resources;

import com.shcepp.shdippsvr.business.bean.BaseResponseBean;

/**
 * 企业信息维护消息
 *
 * @author IvanXu
 * @date 2019-09-03
 **/
public class GuessPlatformInfoRspPojo extends BaseResponseBean {

    private static final long serialVersionUID = -2817573369025992576L;
    private String order;
    private String platformName;
    private String platformProfile;
    private String platformLogoUrl;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getPlatformProfile() {
        return platformProfile;
    }

    public void setPlatformProfile(String platformProfile) {
        this.platformProfile = platformProfile;
    }

    public String getPlatformLogoUrl() {
        return platformLogoUrl;
    }

    public void setPlatformLogoUrl(String platformLogoUrl) {
        this.platformLogoUrl = platformLogoUrl;
    }
}
