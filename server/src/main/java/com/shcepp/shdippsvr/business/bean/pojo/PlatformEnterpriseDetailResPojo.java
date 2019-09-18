package com.shcepp.shdippsvr.business.bean.pojo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Time;

/**
 * Created by shcepp on 2019/9/2.
 */
@Entity
public class PlatformEnterpriseDetailResPojo implements Serializable {

    private static final long serialVersionUID = 2430843537027821861L;
    /**平台名称*/
    private String name;
    /**平台网址*/
    private String website;
    /**平台类型*/
    private String category;
    /**平台简介*/
    private String profile;
    /**企业logo*/
    private String logo;
    /**企业横图*/
    private String image1;
    /**企业竖图*/
    private String image2;
    /**企业名称*/
    private String businessName;
    /**总条数*/
    private long totalCounts;
    /**推荐位顺序*/
    private int order;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public long getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(long totalCounts) {
        this.totalCounts = totalCounts;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }
}
