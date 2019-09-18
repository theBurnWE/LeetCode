package com.shcepp.shdippsvr.business.bean.pojo.recommended;

import com.shcepp.shdippsvr.business.bean.pojo.BaseResPojo;

import java.util.ArrayList;
import java.util.List;

/**
 * 推荐位返回结果bean
 *
 * @author BrunE
 * @date 2019-07-18 18:38
 **/
public class RecommendedResourcesRspHeaderPojo extends BaseResPojo {

    private static final long serialVersionUID = 9198863749822561732L;

    private List<RecommendedResourcesResPojo> homePageRecommendedList = new ArrayList<>();

    private List<RecommendedResourcesResPojo> platRecommendedList= new ArrayList<>();;

    private List<RecommendedResourcesResPojo> companyRecommendedList= new ArrayList<>();;

    private List<RecommendedResourcesResPojo> productRecommendedList= new ArrayList<>();;

    public List<RecommendedResourcesResPojo> getHomePageRecommendedList() {
        return homePageRecommendedList;
    }

    public void setHomePageRecommendedList(List<RecommendedResourcesResPojo> homePageRecommendedList) {
        this.homePageRecommendedList = homePageRecommendedList;
    }

    public List<RecommendedResourcesResPojo> getPlatRecommendedList() {
        return platRecommendedList;
    }

    public void setPlatRecommendedList(List<RecommendedResourcesResPojo> platRecommendedList) {
        this.platRecommendedList = platRecommendedList;
    }

    public List<RecommendedResourcesResPojo> getCompanyRecommendedList() {
        return companyRecommendedList;
    }

    public void setCompanyRecommendedList(List<RecommendedResourcesResPojo> companyRecommendedList) {
        this.companyRecommendedList = companyRecommendedList;
    }

    public List<RecommendedResourcesResPojo> getProductRecommendedList() {
        return productRecommendedList;
    }

    public void setProductRecommendedList(List<RecommendedResourcesResPojo> productRecommendedList) {
        this.productRecommendedList = productRecommendedList;
    }
}
