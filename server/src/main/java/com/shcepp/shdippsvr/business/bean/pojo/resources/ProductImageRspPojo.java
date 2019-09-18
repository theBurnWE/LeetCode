package com.shcepp.shdippsvr.business.bean.pojo.resources;

import com.shcepp.shdippsvr.business.bean.BaseResponseBean;

/**
 * 企业信息维护消息
 *
 * @author IvanXu
 * @date 2019-09-03
 **/
public class ProductImageRspPojo extends BaseResponseBean {

    private static final long serialVersionUID = -4494884618022659987L;
    private String isMedia;
    private String image;

    public String getIsMedia() {
        return isMedia;
    }

    public void setIsMedia(String isMedia) {
        this.isMedia = isMedia;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
