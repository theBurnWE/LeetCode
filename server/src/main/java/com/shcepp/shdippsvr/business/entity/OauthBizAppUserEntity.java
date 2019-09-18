package com.shcepp.shdippsvr.business.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * OauthBizAppUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "OAUTH_BIZ_APP_USER")
public class OauthBizAppUserEntity extends BaseEntity implements java.io.Serializable {
    private static final long serialVersionUID = 3614278177082441695L;
    
    // Fields
    
    private Long id;
    private String loginId;
    private String appCode;
    private Date createDate;
    
    // Constructors
    
    /**
     * default constructor
     */
    public OauthBizAppUserEntity() {
    }
    
    
    // Property accessors
    @Id
    @SequenceGenerator(name = "APP_USER_ID_GENERATOR", sequenceName = "SEQ_OAUTH_BIZ_APP_USER", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APP_USER_ID_GENERATOR")
    @Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name = "LOGIN_ID", nullable = false, length = 300)
    public String getLoginId() {
        return loginId;
    }
    
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
    
    @Column(name = "APP_CODE", nullable = false, length = 300)
    public String getAppCode() {
        return appCode;
    }
    
    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }
    
    @Column(name = "CREATE_DATE")
    public Date getCreateDate() {
        return createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
}