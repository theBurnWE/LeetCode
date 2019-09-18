package com.shcepp.shdippsvr.business.bean.elasticsearch.pojo;

import com.shcepp.shdippsvr.business.bean.elasticsearch.BaseItemPojo;

/**
 * @author zkmao
 * @description
 * @date 2019/9/6 17:08
 */
public class CompanyItemPojo extends BaseItemPojo {
    private static final long serialVersionUID = -6256259817469519677L;
    //企业名称
    private String companyName;
    //统一社会信用代码
    private String usci;
    //官网网址
    private String officalWebsite;
    //所属类别
    private String category;
    //企业规模
    private String scale;
    //企业简介
    private String profile;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUsci() {
        return usci;
    }

    public void setUsci(String usci) {
        this.usci = usci;
    }

    public String getOfficalWebsite() {
        return officalWebsite;
    }

    public void setOfficalWebsite(String officalWebsite) {
        this.officalWebsite = officalWebsite;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }


}
