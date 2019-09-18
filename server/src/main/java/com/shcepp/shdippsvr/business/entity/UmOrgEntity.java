package com.shcepp.shdippsvr.business.entity;
// default package

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


/**
 * UmOrgEntity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "UM_ORG")
public class UmOrgEntity extends BaseEntity implements java.io.Serializable {
    private static final long serialVersionUID = -8590958760946647726L;
    
    
    // Fields    
    
    private Long id;
    private Long orgPid;
    private String orgCode;
    private String name;
    private String flag;
    private String meno;
    private Date createDate;
    private Date modifyDate;
    private String resver1;
    private String resver2;
    private String resver3;
    private String bodyType;
    private String ispOrg;
    
    
    // Constructors
    
    /**
     * default constructor
     */
    public UmOrgEntity() {
    }
    
    
    // Property accessors
    @Id
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getOrgPid() {
        return orgPid;
    }
    
    public void setOrgPid(Long orgPid) {
        this.orgPid = orgPid;
    }
    
    public String getOrgCode() {
        return orgCode;
    }
    
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getFlag() {
        return flag;
    }
    
    public void setFlag(String flag) {
        this.flag = flag;
    }
    
    public String getMeno() {
        return meno;
    }
    
    public void setMeno(String meno) {
        this.meno = meno;
    }
    
    public Date getCreateDate() {
        return createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    public Date getModifyDate() {
        return modifyDate;
    }
    
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
    
    public String getResver1() {
        return resver1;
    }
    
    public void setResver1(String resver1) {
        this.resver1 = resver1;
    }
    
    public String getResver2() {
        return resver2;
    }
    
    public void setResver2(String resver2) {
        this.resver2 = resver2;
    }
    
    public String getResver3() {
        return resver3;
    }
    
    public void setResver3(String resver3) {
        this.resver3 = resver3;
    }
    
    public String getBodyType() {
        return bodyType;
    }
    
    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }
    
    public String getIspOrg() {
        return ispOrg;
    }
    
    public void setIspOrg(String ispOrg) {
        this.ispOrg = ispOrg;
    }
    
    
}