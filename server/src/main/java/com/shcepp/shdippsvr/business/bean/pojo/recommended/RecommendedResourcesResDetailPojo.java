package com.shcepp.shdippsvr.business.bean.pojo.recommended;

import com.shcepp.shdippsvr.business.bean.pojo.BaseResPojo;

/**
 * 首页资源的bean
 *
 * @author BrunE
 * @date 2019-07-18 18:38
 **/
public class RecommendedResourcesResDetailPojo extends BaseResPojo {

    private static final long serialVersionUID = 6723641369925791384L;

    private String disName;
    private String disAbbreviation;
    private String disId;
    private String disUrl;

    public String getDisName() {
        return disName;
    }

    public void setDisName(String disName) {
        this.disName = disName;
    }

    public String getDisAbbreviation() {
        return disAbbreviation;
    }

    public void setDisAbbreviation(String disAbbreviation) {
        this.disAbbreviation = disAbbreviation;
    }

    public String getDisId() {
        return disId;
    }

    public void setDisId(String disId) {
        this.disId = disId;
    }

    public String getDisUrl() {
        return disUrl;
    }

    public void setDisUrl(String disUrl) {
        this.disUrl = disUrl;
    }
}
