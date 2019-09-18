package com.shcepp.shdippsvr.business.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author BrunE
 * @date 2019-08-21 17:23
 **/
@Entity
@Table(name = "SHDIPP_BIZ_RECOMMENDATION")
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
public class ShdippBizRecommendationEntity {

    private String id;
    private String recommType;
    private String lan;
    private String recommContent;
    private String recommNav;
    private String recommId;
    private String itemCategory;
    private long posId;
    private String dataDomain;
    private String status;
    private Date createTime;
    private Date updateTime;
    private String creator;
    private String lastOperator;
    private String sp1;
    private String sp2;
    private String sp3;
    private String title;

    @Id
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "RECOMM_TYPE")
    public String getRecommType() {
        return recommType;
    }

    public void setRecommType(String recommType) {
        this.recommType = recommType;
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
    @Column(name = "RECOMM_CONTENT")
    public String getRecommContent() {
        return recommContent;
    }

    public void setRecommContent(String recommContent) {
        this.recommContent = recommContent;
    }

    @Basic
    @Column(name = "RECOMM_NAV")
    public String getRecommNav() {
        return recommNav;
    }

    public void setRecommNav(String recommNav) {
        this.recommNav = recommNav;
    }

    @Basic
    @Column(name = "RECOMM_ID")
    public String getRecommId() {
        return recommId;
    }

    public void setRecommId(String recommId) {
        this.recommId = recommId;
    }

    @Basic
    @Column(name = "ITEM_CATEGORY")
    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    @Basic
    @Column(name = "POS_ID")
    public long getPosId() {
        return posId;
    }

    public void setPosId(long posId) {
        this.posId = posId;
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

    @Basic
    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
