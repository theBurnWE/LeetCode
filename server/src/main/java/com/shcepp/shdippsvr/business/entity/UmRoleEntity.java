package com.shcepp.shdippsvr.business.entity;
// default package

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


/**
 * UmRoleEntity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "UM_ROLE")
public class UmRoleEntity extends BaseEntity implements java.io.Serializable {
    private static final long serialVersionUID = -3897255213290336524L;
    
    
    // Fields    
    
    private Long id;
    private String name;//角色名称
    private String flag;//0:正常 9:删除
    private String meno;//备注
    private Long subsystemId;//子系统Id
    private Date createDate;//创建日期
    private Date modifyDate;//修改日期
    private String roleCode;//角色code
    private String towinxportal;
    
    
    // Constructors
    
    /**
     * default constructor
     */
    public UmRoleEntity() {
    }
    
    
    // Property accessors
    @Id
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
    
    public Long getSubsystemId() {
        return subsystemId;
    }
    
    public void setSubsystemId(Long subsystemId) {
        this.subsystemId = subsystemId;
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
    
    public String getRoleCode() {
        return roleCode;
    }
    
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
    
    public String getTowinxportal() {
        return towinxportal;
    }
    
    public void setTowinxportal(String towinxportal) {
        this.towinxportal = towinxportal;
    }
    
    
}