package com.shcepp.shdippsvr.business.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author BrunE
 * @date 2019-08-21 17:23
 **/
@Entity
@Table(name = "SHDIPP_BIZ_MESSAGE")
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)

public class ShdippBizMessageEntity {

    private String id;
    private String msgCategory;
    private String notifyUserId;
    private String readFlag;
    private String msgParams;
    private String msgDelFlag;
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
    @Column(name = "MSG_CATEGORY")
    public String getMsgCategory() {
        return msgCategory;
    }

    public void setMsgCategory(String msgCategory) {
        this.msgCategory = msgCategory;
    }

    @Basic
    @Column(name = "NOTIFY_USER_ID")
    public String getNotifyUserId() {
        return notifyUserId;
    }

    public void setNotifyUserId(String notifyUserId) {
        this.notifyUserId = notifyUserId;
    }

    @Basic
    @Column(name = "READ_FLAG")
    public String getReadFlag() {
        return readFlag;
    }

    public void setReadFlag(String readFlag) {
        this.readFlag = readFlag;
    }

    @Basic
    @Column(name = "MSG_PARAMS")
    public String getMsgParams() {
        return msgParams;
    }

    public void setMsgParams(String msgParams) {
        this.msgParams = msgParams;
    }

    @Basic
    @Column(name = "MSG_DEL_FLAG")
    public String getMsgDelFlag() {
        return msgDelFlag;
    }

    public void setMsgDelFlag(String msgDelFlag) {
        this.msgDelFlag = msgDelFlag;
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
