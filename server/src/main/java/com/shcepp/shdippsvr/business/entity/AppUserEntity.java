package com.shcepp.shdippsvr.business.entity;



import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @description:
 * @author: zkmao
 * @date: 2018/8/24
 */
@Entity
//UA.ID,UA.LOGIN_ID,A.APP_CODE,A.APP_NAME,A.DOMAIN,A.WEB_URL,A.ICON_URL,A.ORDERNO,A.APPTYPE
public class AppUserEntity extends BaseEntity{
    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
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
    @Column(name = "APP_CODE")
    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    @Basic
    @Column(name = "APP_NAME")
    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    @Basic
    @Column(name = "DOMAIN")
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Basic
    @Column(name = "WEB_URL")
    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    @Basic
    @Column(name = "ICON_URL")
    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @Basic
    @Column(name = "ORDERNO")
    public int getOrderno() {
        return orderno;
    }

    public void setOrderno(int orderno) {
        this.orderno = orderno;
    }

    @Basic
    @Column(name = "APPTYPE")
    public String getApptype() {
        return apptype;
    }

    public void setApptype(String apptype) {
        this.apptype = apptype;
    }

    private long id;
    private String loginId;
    private String appCode;
    private String appName;
    private String domain;
    private String webUrl;
    private String iconUrl;
    private int orderno;
    private String apptype;
}
