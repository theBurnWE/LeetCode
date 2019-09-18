package com.shcepp.shdippsvr.business.entity.view;

import javax.persistence.*;
import java.util.Date;

/**
 * @author BrunE
 * @date 2019-09-02 10:37
 **/
@Entity
@Table(name = "V_PRODUCT_INFO_DETAIL")
public class VProductInfoDetailEntity {

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
    private Date createTime;
    private Date updateTime;
    private String creator;
    private String lastOperator;
    private String sp1;
    private String sp2;
    private String sp3;
    private String id;
    private String prdId;
    private String productName;
    private Date putonTime;
    private String category;
    private String registryStatus;
    private String copyrightNumber;
    private String region;
    private String image1;
    private String image2;
    private String summary;
    private String bizdStatus;
    private String bizdAuditComment;
    private Date bizdAuditTime;
    private Date bizdCreateTime;
    private Date bizdUpdateTime;
    private String bizdCreator;
    private String bizdLastOperator;
    private String bizdSp1;
    private String bizdSp2;
    private String bizdSp3;

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
    @Column(name = "PRD_ID")
    public String getPrdId() {
        return prdId;
    }

    public void setPrdId(String prdId) {
        this.prdId = prdId;
    }

    @Basic
    @Column(name = "PRODUCT_NAME")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "PUTON_TIME")
    public Date getPutonTime() {
        return putonTime;
    }

    public void setPutonTime(Date putonTime) {
        this.putonTime = putonTime;
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
    @Column(name = "REGISTRY_STATUS")
    public String getRegistryStatus() {
        return registryStatus;
    }

    public void setRegistryStatus(String registryStatus) {
        this.registryStatus = registryStatus;
    }

    @Basic
    @Column(name = "COPYRIGHT_NUMBER")
    public String getCopyrightNumber() {
        return copyrightNumber;
    }

    public void setCopyrightNumber(String copyrightNumber) {
        this.copyrightNumber = copyrightNumber;
    }

    @Basic
    @Column(name = "REGION")
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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
    @Column(name = "SUMMARY")
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Basic
    @Column(name = "BIZD_STATUS")
    public String getBizdStatus() {
        return bizdStatus;
    }

    public void setBizdStatus(String bizdStatus) {
        this.bizdStatus = bizdStatus;
    }

    @Basic
    @Column(name = "BIZD_AUDIT_COMMENT")
    public String getBizdAuditComment() {
        return bizdAuditComment;
    }

    public void setBizdAuditComment(String bizdAuditComment) {
        this.bizdAuditComment = bizdAuditComment;
    }

    @Basic
    @Column(name = "BIZD_AUDIT_TIME")
    public Date getBizdAuditTime() {
        return bizdAuditTime;
    }

    public void setBizdAuditTime(Date bizdAuditTime) {
        this.bizdAuditTime = bizdAuditTime;
    }

    @Basic
    @Column(name = "BIZD_CREATE_TIME")
    public Date getBizdCreateTime() {
        return bizdCreateTime;
    }

    public void setBizdCreateTime(Date bizdCreateTime) {
        this.bizdCreateTime = bizdCreateTime;
    }

    @Basic
    @Column(name = "BIZD_UPDATE_TIME")
    public Date getBizdUpdateTime() {
        return bizdUpdateTime;
    }

    public void setBizdUpdateTime(Date bizdUpdateTime) {
        this.bizdUpdateTime = bizdUpdateTime;
    }

    @Basic
    @Column(name = "BIZD_CREATOR")
    public String getBizdCreator() {
        return bizdCreator;
    }

    public void setBizdCreator(String bizdCreator) {
        this.bizdCreator = bizdCreator;
    }

    @Basic
    @Column(name = "BIZD_LAST_OPERATOR")
    public String getBizdLastOperator() {
        return bizdLastOperator;
    }

    public void setBizdLastOperator(String bizdLastOperator) {
        this.bizdLastOperator = bizdLastOperator;
    }

    @Basic
    @Column(name = "BIZD_SP1")
    public String getBizdSp1() {
        return bizdSp1;
    }

    public void setBizdSp1(String bizdSp1) {
        this.bizdSp1 = bizdSp1;
    }

    @Basic
    @Column(name = "BIZD_SP2")
    public String getBizdSp2() {
        return bizdSp2;
    }

    public void setBizdSp2(String bizdSp2) {
        this.bizdSp2 = bizdSp2;
    }

    @Basic
    @Column(name = "BIZD_SP3")
    public String getBizdSp3() {
        return bizdSp3;
    }

    public void setBizdSp3(String bizdSp3) {
        this.bizdSp3 = bizdSp3;
    }

}
