package com.shcepp.shdippsvr.business.bean.pojo;

/**
 * Created by gxd on 2019/9/2.
 */
public class PlatformEnterpriseDetailReqPojo {
    /**产品类型*/
    private String category;
    /**种类*/
    private String businessType;
    /**语言代码*/
    private String lan;
    /**主站分站*/
    private String dataDomain;
    /**关键字*/
    private String keyWord;
   /**路由请求地址*/
    private String baseUrl;
    /**最大条数*/
    private long maxRowCounts;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getLan() {
        return lan;
    }

    public void setLan(String lan) {
        this.lan = lan;
    }

    public String getDataDomain() {
        return dataDomain;
    }

    public void setDataDomain(String dataDomain) {
        this.dataDomain = dataDomain;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public long getMaxRowCounts() {
        return maxRowCounts;
    }

    public void setMaxRowCounts(long maxRowCounts) {
        this.maxRowCounts = maxRowCounts;
    }
}
