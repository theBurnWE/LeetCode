package com.shcepp.shdippsvr.business.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * UmBizAuditEntity entity. @author MyEclipse Persistence Tools
 */

@Entity
@Table(name = "UM_BIZ_AUDIT")
public class UmBizAuditEntity extends BaseEntity implements java.io.Serializable {
    private static final long serialVersionUID = -5416560890944744802L;
    
    // Fields
    
    private Long id;
    private String loginId;
    private String auditOperation;
    private String auditPermission;
    private Long operId;
    private String operType;
    private String flag;
    private String description;
    private String dataDescription1;
    private String dataDescription2;
    private Date recordTime;
    
    // Constructors
    
    /**
     * default constructor
     */
    public UmBizAuditEntity() {
    }
    
    
    // Property accessors
    @Id
    @SequenceGenerator(name = "BIZ_AUDIT_ID_GENERATOR", sequenceName = "SEQ_UM_BIZ_AUDIT", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BIZ_AUDIT_ID_GENERATOR")
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getLoginId() {
        return loginId;
    }
    
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
    
    public String getAuditOperation() {
        return auditOperation;
    }
    
    public void setAuditOperation(String auditOperation) {
        this.auditOperation = auditOperation;
    }
    
    public String getAuditPermission() {
        return auditPermission;
    }
    
    public void setAuditPermission(String auditPermission) {
        this.auditPermission = auditPermission;
    }
    
    public Long getOperId() {
        return operId;
    }
    
    public void setOperId(Long operId) {
        this.operId = operId;
    }
    
    public String getOperType() {
        return operType;
    }
    
    public void setOperType(String operType) {
        this.operType = operType;
    }
    
    public String getFlag() {
        return flag;
    }
    
    public void setFlag(String flag) {
        this.flag = flag;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDataDescription1() {
        return dataDescription1;
    }
    
    public void setDataDescription1(String dataDescription1) {
        this.dataDescription1 = dataDescription1;
    }
    
    public String getDataDescription2() {
        return dataDescription2;
    }
    
    public void setDataDescription2(String dataDescription2) {
        this.dataDescription2 = dataDescription2;
    }
    
    public Date getRecordTime() {
        return recordTime;
    }
    
    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }
    
}