package com.shcepp.shdippsvr.business.entity;

// default package

import javax.persistence.*;
import java.util.Date;

/**
 * UmUserEntity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "UM_USER")
public class UmUserEntity extends BaseEntity implements java.io.Serializable {
    private static final long serialVersionUID = -2054077170792429662L;
    
    // Fields
    
    private Long id;
    private Long orgId;// 组织id
    private String loginId;// 登录名
    private String name;// 用户名
    private String loginPass;// 用户密码
    private String mail;// 邮箱
    private String address;// 地址
    private String userLevel;// 9:超级管理员，1:子系统管理员，2:普通用户
    private String zip;// 邮编
    private String tele;// 电话
    private String mobile;// 手机
    private String flag;// 状态
    private String meno;// 备注
    private Date createDate;// 创建日期
    private Date modifyDate;// 修改日期
    private Date loginDate;// 登录日期
    private String sex;// 性别
    private Date logoutDate;// 退出时间
    private Date hangupDate;// 上次登录错误时间
    private Long errorTimes;// 连续登录出错累计次数
    private Long loginTimes;// 累计登录成功次数
    private String lastIp;// 最后登录IP地址
    private String pwdType;// 密码算法
    private String userType;// 用户类型
    private String ispOrg;// 服务商来源
    private String bodyType;// 集团类型
    
    // Constructors
    
    /**
     * default constructor
     */
    public UmUserEntity() {
    }
    
    // Property accessors
    @Id
    @SequenceGenerator(name = "UUMMUSER_ID_GENERATOR", sequenceName = "SEQ_UM_USER", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UUMMUSER_ID_GENERATOR")
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getOrgId() {
        return orgId;
    }
    
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
    
    public String getLoginId() {
        return loginId;
    }
    
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getLoginPass() {
        return loginPass;
    }
    
    public void setLoginPass(String loginPass) {
        this.loginPass = loginPass;
    }
    
    public String getMail() {
        return mail;
    }
    
    public void setMail(String mail) {
        this.mail = mail;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getUserLevel() {
        return userLevel;
    }
    
    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }
    
    public String getZip() {
        return zip;
    }
    
    public void setZip(String zip) {
        this.zip = zip;
    }
    
    public String getTele() {
        return tele;
    }
    
    public void setTele(String tele) {
        this.tele = tele;
    }
    
    public String getMobile() {
        return mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
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
    
    public Date getLoginDate() {
        return loginDate;
    }
    
    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }
    
    public String getSex() {
        return sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    public Date getLogoutDate() {
        return logoutDate;
    }
    
    public void setLogoutDate(Date logoutDate) {
        this.logoutDate = logoutDate;
    }
    
    public Date getHangupDate() {
        return hangupDate;
    }
    
    public void setHangupDate(Date hangupDate) {
        this.hangupDate = hangupDate;
    }
    
    public Long getErrorTimes() {
        return errorTimes;
    }
    
    public void setErrorTimes(Long errorTimes) {
        this.errorTimes = errorTimes;
    }
    
    public Long getLoginTimes() {
        return loginTimes;
    }
    
    public void setLoginTimes(Long loginTimes) {
        this.loginTimes = loginTimes;
    }
    
    public String getLastIp() {
        return lastIp;
    }
    
    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }
    
    public String getPwdType() {
        return pwdType;
    }
    
    public void setPwdType(String pwdType) {
        this.pwdType = pwdType;
    }
    
    public String getUserType() {
        return userType;
    }
    
    public void setUserType(String userType) {
        this.userType = userType;
    }
    
    public String getIspOrg() {
        return ispOrg;
    }
    
    public void setIspOrg(String ispOrg) {
        this.ispOrg = ispOrg;
    }
    
    public String getBodyType() {
        return bodyType;
    }
    
    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }
    
}