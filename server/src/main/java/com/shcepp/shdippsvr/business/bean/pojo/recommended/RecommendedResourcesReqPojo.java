package com.shcepp.shdippsvr.business.bean.pojo.recommended;

import com.shcepp.shdippsvr.business.bean.pojo.BaseReqPojo;

/**
 * 推荐位请求资源的bean
 *
 * @author BrunE
 * @date 2019-07-18 18:38
 **/
public class RecommendedResourcesReqPojo extends BaseReqPojo {

    private static final long serialVersionUID = -5341750282032372646L;

    private static final String SHOW = "1";
    private static final String HIDDEN = "0";

    /**
     * 模块list
     */
    private String moduleTypeArray;
    /**
     * 推荐位状态，默认为展示中
     */
    private String recommendationStatus;
    /**
     * 本次查询的模块
     */
    private String moduleType;

    /**
     * 明细数据的ID用以进行精确查找
     */
    private String detailId;

    public String getModuleTypeArray() {
        return moduleTypeArray;
    }

    public void setModuleTypeArray(String moduleTypeArray) {
        this.moduleTypeArray = moduleTypeArray;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getRecommendationStatus() {
        return recommendationStatus;
    }

    public void setRecommendationStatus(String recommendationStatus) {
        this.recommendationStatus = recommendationStatus;
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }
}
