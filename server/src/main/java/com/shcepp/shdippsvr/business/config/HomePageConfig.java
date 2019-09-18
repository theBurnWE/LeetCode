package com.shcepp.shdippsvr.business.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by apple on 2017/8/1.
 */

@Component
public class HomePageConfig implements BaseConfig {

    //首页的平台推荐位数量
    public static long homepagePlatSize;

    //首页的企业推荐位数量
    public static long homepageCompanySize;

    //首页的产品推荐位数量
    public static long homePageRrodeceSize;
    //首页的首页推荐位数量
    public static long homePageHomePageSize;

    //首页简述最大长度
    public static int pageAbbreviationLength;

    @Value("${shdippsvrcache.homepagePlatSize:6}")
    public void setHomepagePlatSize(long homepagePlatSize) {
        HomePageConfig.homepagePlatSize = homepagePlatSize;
    }

    @Value("${shdippsvrcache.homepageCompanySize:6}")
    public void setHomepageCompanySize(long homepageCompanySize) {
        HomePageConfig.homepageCompanySize = homepageCompanySize;
    }

    @Value("${shdippsvrcache.homePageRrodeceSize:14}")
    public void setHomePageRrodeceSize(long homePageRrodeceSize) {
        HomePageConfig.homePageRrodeceSize = homePageRrodeceSize;
    }

    @Value("${shdippsvrcache.homePageHomePageSize:6}")
    public void setHomePageHomePageSize(long homePageHomePageSize) {
        HomePageConfig.homePageHomePageSize = homePageHomePageSize;
    }

    @Value("${shdippsvrcache.pageAbbreviationLength:200}")
    public void setPageAbbreviationLength(int pageAbbreviationLength) {
        HomePageConfig.pageAbbreviationLength = pageAbbreviationLength;
    }
}
