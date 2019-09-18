package com.shcepp.shdippsvr.business.bean.elasticsearch.pojo;

import com.shcepp.shdippsvr.business.bean.elasticsearch.BaseItemPojo;

/**
 * @author zkmao
 * @description
 * @date 2019/9/6 17:08
 */
public class ProductItemPojo extends BaseItemPojo {

    private static final long serialVersionUID = 4957094643447829719L;
    //产品名称
    private String productName;

    //所属类别
    private String category;
    //版权登记状态
    private String registryStatus;
    //版权号
    private String copyrightNo;
    //所有权范围
    private String region;
    //产品摘要
    private String summary;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRegistryStatus() {
        return registryStatus;
    }

    public void setRegistryStatus(String registryStatus) {
        this.registryStatus = registryStatus;
    }

    public String getCopyrightNo() {
        return copyrightNo;
    }

    public void setCopyrightNo(String copyrightNo) {
        this.copyrightNo = copyrightNo;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }


}
