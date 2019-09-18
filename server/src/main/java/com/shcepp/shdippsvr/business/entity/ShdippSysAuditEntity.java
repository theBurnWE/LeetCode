package com.shcepp.shdippsvr.business.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author BrunE
 * @date 2019-08-21 17:23
 **/
@Entity
@Table(name = "SHDIPP_SYS_AUDIT")
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)

public class ShdippSysAuditEntity {

    private String id;
    private String loginId;
    private String auditOperation;
    private String auditPermission;
    private String operId;
    private String operType;
    private String flag;
    private String description;
    private String dataDescription1;
    private String dataDescription2;
    private Date recordTime;
    private String clientIp;
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
    @Column(name = "LOGIN_ID")
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    @Basic
    @Column(name = "AUDIT_OPERATION")
    public String getAuditOperation() {
        return auditOperation;
    }

    public void setAuditOperation(String auditOperation) {
        this.auditOperation = auditOperation;
    }

    @Basic
    @Column(name = "AUDIT_PERMISSION")
    public String getAuditPermission() {
        return auditPermission;
    }

    public void setAuditPermission(String auditPermission) {
        this.auditPermission = auditPermission;
    }

    @Basic
    @Column(name = "OPER_ID")
    public String getOperId() {
        return operId;
    }

    public void setOperId(String operId) {
        this.operId = operId;
    }

    @Basic
    @Column(name = "OPER_TYPE")
    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    @Basic
    @Column(name = "FLAG")
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "DATA_DESCRIPTION1")
    public String getDataDescription1() {
        return dataDescription1;
    }

    public void setDataDescription1(String dataDescription1) {
        this.dataDescription1 = dataDescription1;
    }

    @Basic
    @Column(name = "DATA_DESCRIPTION2")
    public String getDataDescription2() {
        return dataDescription2;
    }

    public void setDataDescription2(String dataDescription2) {
        this.dataDescription2 = dataDescription2;
    }

    @Basic
    @Column(name = "RECORD_TIME")
    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    @Basic
    @Column(name = "CLIENT_IP")
    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
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
