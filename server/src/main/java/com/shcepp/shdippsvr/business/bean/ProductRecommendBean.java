package com.shcepp.shdippsvr.business.bean;

/**
 * 企业信息维护消息
 *
 * @author IvanXu
 * @date 2019-09-03
 **/
public class ProductRecommendBean extends BaseBean {

    private static final long serialVersionUID = -5069059462451705966L;

    private String productOneId;
    private String productTwoId;
    private String productThreeId;
    private String productFourId;

    public String getProductOneId() {
        return productOneId;
    }

    public void setProductOneId(String productOneId) {
        this.productOneId = productOneId;
    }

    public String getProductTwoId() {
        return productTwoId;
    }

    public void setProductTwoId(String productTwoId) {
        this.productTwoId = productTwoId;
    }

    public String getProductThreeId() {
        return productThreeId;
    }

    public void setProductThreeId(String productThreeId) {
        this.productThreeId = productThreeId;
    }

    public String getProductFourId() {
        return productFourId;
    }

    public void setProductFourId(String productFourId) {
        this.productFourId = productFourId;
    }
}
