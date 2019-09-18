package com.shcepp.shdippsvr.business.bean;

import com.shcepp.shdippsvr.business.bean.elasticsearch.BaseItemPojo;

/**
 * 富文本产品详情描述
 *
 * @author IvanXu
 * @date 2019-09-03
 **/
public class ProductDetailDesBean extends BaseItemPojo {

    private static final long serialVersionUID = -7761278601608870136L;

    private String describe;
    private String logoUrl;

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
