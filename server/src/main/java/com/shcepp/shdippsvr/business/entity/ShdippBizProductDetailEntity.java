package com.shcepp.shdippsvr.business.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author BrunE
 * @date 2019-08-21 17:23
 **/
@Entity
@Table(name = "SHDIPP_BIZ_PRODUCT_DETAIL")
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)

public class ShdippBizProductDetailEntity {

    public static final String STATUS_0 = "0";     //未上架
    public static final String STATUS_1 = "1";     //正常
    public static final String STATUS_2 = "2";     //待审核
    public static final String STATUS_9 = "9";     //审核驳回

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
    private String status;
    private String auditComment;
    private Date auditTime;
    private Date createTime;
    private Date updateTime;
    private String creator;
    private String lastOperator;
    private String sp1;
    private String sp2;
    private String sp3;

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
    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

}
