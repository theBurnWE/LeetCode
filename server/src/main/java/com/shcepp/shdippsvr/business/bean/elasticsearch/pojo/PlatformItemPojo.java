package com.shcepp.shdippsvr.business.bean.elasticsearch.pojo;

import com.shcepp.shdippsvr.business.bean.elasticsearch.BaseItemPojo;

/**
 * @author zkmao
 * @description
 * @date 2019/9/6 17:08
 */
public class PlatformItemPojo extends BaseItemPojo {

    private static final long serialVersionUID = -4442283820474143795L;

    /**
     * 平台名称
     */
    private String name;
    /**
     * 平台网址
     */
    private String website;


    /**
     * 平台类型
     */

    private String category;
    /**
     * 平台简介
     */
    private String profile;
    //平台功能
    private String function;

    //企业名称
    private String companyName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
}
