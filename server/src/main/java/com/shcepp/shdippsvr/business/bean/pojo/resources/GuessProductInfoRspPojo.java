package com.shcepp.shdippsvr.business.bean.pojo.resources;

import com.shcepp.shdippsvr.business.bean.BaseResponseBean;

/**
 * 企业信息维护消息
 *
 * @author IvanXu
 * @date 2019-09-03
 **/
public class GuessProductInfoRspPojo extends BaseResponseBean {

    private static final long serialVersionUID = -6002106353792130436L;

    private String order;
    private String productName;
    private String productSummary;
    private String productImageUrl;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSummary() {
        return productSummary;
    }

    public void setProductSummary(String productSummary) {
        this.productSummary = productSummary;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }
}
