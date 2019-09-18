package com.shcepp.shdippsvr.business.bean.pojo.resources;

import com.shcepp.shdippsvr.business.bean.BaseRequestBean;
import com.shcepp.shdippsvr.business.bean.BaseResponseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 企业信息维护消息
 *
 * @author IvanXu
 * @date 2019-08-29
 **/
public class CompanyInfoRspPojo extends BaseResponseBean {

    private static final long serialVersionUID = 7069798352030236017L;

    private String lan;
    private String name;
    private String usci;
    private String officialWebsite;
    private String tele;
    private String[] category;
    private String scale;
    private String profile;
    private String logo;
    private String image1;
    private String image2;
    private String media;
    private String status;

    public String getLan() {
        return lan;
    }

    public void setLan(String lan) {
        this.lan = lan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsci() {
        return usci;
    }

    public void setUsci(String usci) {
        this.usci = usci;
    }

    public String getOfficialWebsite() {
        return officialWebsite;
    }

    public void setOfficialWebsite(String officialWebsite) {
        this.officialWebsite = officialWebsite;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String[] getCategory() {
        return category;
    }

    public void setCategory(String[] category) {
        this.category = category;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
