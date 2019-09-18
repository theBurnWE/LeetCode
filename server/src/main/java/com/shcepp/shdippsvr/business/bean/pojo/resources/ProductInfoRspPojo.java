package com.shcepp.shdippsvr.business.bean.pojo.resources;

import com.shcepp.shdippsvr.business.bean.BaseResponseBean;

import java.util.List;

/**
 * 企业信息维护消息
 *
 * @author IvanXu
 * @date 2019-09-03
 **/
public class ProductInfoRspPojo extends BaseResponseBean {

    private static final long serialVersionUID = 6108850817993548362L;
    private String productName;
    private int likeCount;
    private String favoriteFlag;
    private String[] categoryArr;
    private List<ProductImageRspPojo> images;
    private String putonTime;
    private String region;
    private String copyrightNumber;
    private String companyName;
    private String summary;
    private String name;
    private String email;
    private String officeAddr;
    private String tele;
    private GuessCompanyInfoRspPojo guessCompanyInfo;
    private List<GuessPlatformInfoRspPojo> guessPlatformInfoList;
    private List<GuessProductInfoRspPojo> guessProductInfoList;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getFavoriteFlag() {
        return favoriteFlag;
    }

    public void setFavoriteFlag(String favoriteFlag) {
        this.favoriteFlag = favoriteFlag;
    }

    public String[] getCategoryArr() {
        return categoryArr;
    }

    public void setCategoryArr(String[] categoryArr) {
        this.categoryArr = categoryArr;
    }


    public List<ProductImageRspPojo> getImages() {
        return images;
    }

    public void setImages(List<ProductImageRspPojo> images) {
        this.images = images;
    }

    public String getPutonTime() {
        return putonTime;
    }

    public void setPutonTime(String putonTime) {
        this.putonTime = putonTime;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCopyrightNumber() {
        return copyrightNumber;
    }

    public void setCopyrightNumber(String copyrightNumber) {
        this.copyrightNumber = copyrightNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOfficeAddr() {
        return officeAddr;
    }

    public void setOfficeAddr(String officeAddr) {
        this.officeAddr = officeAddr;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public GuessCompanyInfoRspPojo getGuessCompanyInfo() {
        return guessCompanyInfo;
    }

    public void setGuessCompanyInfo(GuessCompanyInfoRspPojo guessCompanyInfo) {
        this.guessCompanyInfo = guessCompanyInfo;
    }

    public List<GuessPlatformInfoRspPojo> getGuessPlatformInfoList() {
        return guessPlatformInfoList;
    }

    public void setGuessPlatformInfoList(List<GuessPlatformInfoRspPojo> guessPlatformInfoList) {
        this.guessPlatformInfoList = guessPlatformInfoList;
    }

    public List<GuessProductInfoRspPojo> getGuessProductInfoList() {
        return guessProductInfoList;
    }

    public void setGuessProductInfoList(List<GuessProductInfoRspPojo> guessProductInfoList) {
        this.guessProductInfoList = guessProductInfoList;
    }
}
