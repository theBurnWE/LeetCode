package com.shcepp.shdippsvr.business.entity.view;

import javax.persistence.*;
import java.util.Date;

/**
 * @author BrunE
 * @date 2019-09-02 10:37
 **/
@Entity
@Table(name = "V_PLATFORM_INFO_DETAIL")
public class VPlatformInfoDetailEntity {
    /**审核状态 1正常*/
    public static final String STATUS_TRUE = "1";
    private String bizreRecommType;
    private String bizreLan;
    private String bizreRecommContent;
    private String bizreRecommNav;
    private String bizreRecommId;
    private String bizreItemCategory;
    private long bizrePosId;
    private String bizreDataDomain;
    private String bizreStatus;
    private String userId;
    private String lan;
    private String dataDomain;
    private String status;
    private String delFlag;
    private Date bizCreteTime;
    private Date bizUpdateTime;
    private String bizCreator;
    private String bizLastOperator;
    private String sp1;
    private String sp2;
    private String sp3;
    private String id;
    private String platformId;
    private String name;
    private String website;
    private String category;
    private String profile;
    private String function;
    private String logo;
    private String image1;
    private String image2;
    private String auditComment;
    private Date auditTime;
    private Date createTime;
    private Date updateTime;
    private String creator;
    private String lastOperator;

    @Basic
    @Column(name = "BIZRE_RECOMM_TYPE")
    public String getBizreRecommType() {
        return bizreRecommType;
    }

    public void setBizreRecommType(String bizreRecommType) {
        this.bizreRecommType = bizreRecommType;
    }

    @Basic
    @Column(name = "BIZRE_LAN")
    public String getBizreLan() {
        return bizreLan;
    }

    public void setBizreLan(String bizreLan) {
        this.bizreLan = bizreLan;
    }

    @Basic
    @Column(name = "BIZRE_RECOMM_CONTENT")
    public String getBizreRecommContent() {
        return bizreRecommContent;
    }

    public void setBizreRecommContent(String bizreRecommContent) {
        this.bizreRecommContent = bizreRecommContent;
    }

    @Basic
    @Column(name = "BIZRE_RECOMM_NAV")
    public String getBizreRecommNav() {
        return bizreRecommNav;
    }

    public void setBizreRecommNav(String bizreRecommNav) {
        this.bizreRecommNav = bizreRecommNav;
    }

    @Basic
    @Column(name = "BIZRE_RECOMM_ID")
    public String getBizreRecommId() {
        return bizreRecommId;
    }

    public void setBizreRecommId(String bizreRecommId) {
        this.bizreRecommId = bizreRecommId;
    }

    @Basic
    @Column(name = "BIZRE_ITEM_CATEGORY")
    public String getBizreItemCategory() {
        return bizreItemCategory;
    }

    public void setBizreItemCategory(String bizreItemCategory) {
        this.bizreItemCategory = bizreItemCategory;
    }

    @Basic
    @Column(name = "BIZRE_POS_ID")
    public long getBizrePosId() {
        return bizrePosId;
    }

    public void setBizrePosId(long bizrePosId) {
        this.bizrePosId = bizrePosId;
    }

    @Basic
    @Column(name = "BIZRE_DATA_DOMAIN")
    public String getBizreDataDomain() {
        return bizreDataDomain;
    }

    public void setBizreDataDomain(String bizreDataDomain) {
        this.bizreDataDomain = bizreDataDomain;
    }

    @Basic
    @Column(name = "BIZRE_STATUS")
    public String getBizreStatus() {
        return bizreStatus;
    }

    public void setBizreStatus(String bizreStatus) {
        this.bizreStatus = bizreStatus;
    }

    @Basic
    @Column(name = "USER_ID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "LAN")
    public String getLan() {
        return lan;
    }

    public void setLan(String lan) {
        this.lan = lan;
    }

    @Basic
    @Column(name = "DATA_DOMAIN")
    public String getDataDomain() {
        return dataDomain;
    }

    public void setDataDomain(String dataDomain) {
        this.dataDomain = dataDomain;
    }

    @Basic
    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "DEL_FLAG")
    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @Basic
    @Column(name = "BIZ_CRETE_TIME")
    public Date getBizCreteTime() {
        return bizCreteTime;
    }

    public void setBizCreteTime(Date bizCreteTime) {
        this.bizCreteTime = bizCreteTime;
    }

    @Basic
    @Column(name = "BIZ_UPDATE_TIME")
    public Date getBizUpdateTime() {
        return bizUpdateTime;
    }

    public void setBizUpdateTime(Date bizUpdateTime) {
        this.bizUpdateTime = bizUpdateTime;
    }

    @Basic
    @Column(name = "BIZ_CREATOR")
    public String getBizCreator() {
        return bizCreator;
    }

    public void setBizCreator(String bizCreator) {
        this.bizCreator = bizCreator;
    }

    @Basic
    @Column(name = "BIZ_LAST_OPERATOR")
    public String getBizLastOperator() {
        return bizLastOperator;
    }

    public void setBizLastOperator(String bizLastOperator) {
        this.bizLastOperator = bizLastOperator;
    }

    @Basic
    @Column(name = "SP1")
    public String getSp1() {
        return sp1;
    }

    public void setSp1(String sp1) {
        this.sp1 = sp1;
    }

    @Basic
    @Column(name = "SP2")
    public String getSp2() {
        return sp2;
    }

    public void setSp2(String sp2) {
        this.sp2 = sp2;
    }

    @Basic
    @Column(name = "SP3")
    public String getSp3() {
        return sp3;
    }

    public void setSp3(String sp3) {
        this.sp3 = sp3;
    }
    @Id
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PLATFORM_ID")
    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "WEBSITE")
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }


    @Basic
    @Column(name = "CATEGORY")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Basic
    @Column(name = "PROFILE")
    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    @Basic
    @Column(name = "FUNCTION")
    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    @Basic
    @Column(name = "LOGO")
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Basic
    @Column(name = "IMAGE1")
    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    @Basic
    @Column(name = "IMAGE2")
    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    @Basic
    @Column(name = "AUDIT_COMMENT")
    public String getAuditComment() {
        return auditComment;
    }

    public void setAuditComment(String auditComment) {
        this.auditComment = auditComment;
    }

    @Basic
    @Column(name = "AUDIT_TIME")
    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    @Basic
    @Column(name = "CREATE_TIME")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "UPDATE_TIME")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "CREATOR")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Basic
    @Column(name = "LAST_OPERATOR")
    public String getLastOperator() {
        return lastOperator;
    }

    public void setLastOperator(String lastOperator) {
        this.lastOperator = lastOperator;
    }

}
