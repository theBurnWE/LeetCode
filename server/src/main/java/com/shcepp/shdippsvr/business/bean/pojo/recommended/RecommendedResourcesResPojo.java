package com.shcepp.shdippsvr.business.bean.pojo.recommended;

import com.shcepp.shdippsvr.business.bean.pojo.BaseResPojo;

import java.util.List;

/**
 * 首页推荐位单项的bean
 *
 * @author BrunE
 * @date 2019-07-18 18:38
 **/
public class RecommendedResourcesResPojo extends BaseResPojo {

    private static final long serialVersionUID = -3360437016516510473L;
    private String transversePicUrlPicUrl;
    private String portraitPicUrl;
    private String logoUrl;
    private String name;
    private String jumpLink;
    private String abbreviation;
    private String id;
    private String describe;
    private String businessType;
    private String moduleType;
    private String userId;
    private long sort;
    private List<RecommendedResourcesResDetailPojo> disList;

    public String getTransversePicUrlPicUrl() {
        return transversePicUrlPicUrl;
    }

    public void setTransversePicUrlPicUrl(String transversePicUrlPicUrl) {
        this.transversePicUrlPicUrl = transversePicUrlPicUrl;
    }

    public String getPortraitPicUrl() {
        return portraitPicUrl;
    }

    public void setPortraitPicUrl(String portraitPicUrl) {
        this.portraitPicUrl = portraitPicUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJumpLink() {
        return jumpLink;
    }

    public void setJumpLink(String jumpLink) {
        this.jumpLink = jumpLink;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getSort() {
        return sort;
    }

    public void setSort(long sort) {
        this.sort = sort;
    }

    public List<RecommendedResourcesResDetailPojo> getDisList() {
        return disList;
    }

    public void setDisList(List<RecommendedResourcesResDetailPojo> disList) {
        this.disList = disList;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
