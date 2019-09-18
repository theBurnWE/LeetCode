package com.shcepp.shdippsvr.business.entity;
// default package


import javax.persistence.*;

/**
 * UmUserRoleEntity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "UM_USER_ROLE")
public class UmUserRoleEntity extends BaseEntity implements java.io.Serializable {
    private static final long serialVersionUID = -2153414531155530757L;
    
    
    // Fields    
    
    private Long id;
    private Long userId;
    private Long roleId;
    
    
    // Constructors
    
    /**
     * default constructor
     */
    public UmUserRoleEntity() {
    }
    
    
    // Property accessors
    @Id
    @SequenceGenerator(name = "UUMMUSER_ROLE_ID_GENERATOR", sequenceName = "SEQ_UM_USER_ROLE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UUMMUSER_ROLE_ID_GENERATOR")
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public Long getRoleId() {
        return roleId;
    }
    
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    
    
}